package tech.buildrun.core.usecase;

import org.springframework.stereotype.Component;
import tech.buildrun.adapter.in.web.dto.AnalyticsDayResponse;
import tech.buildrun.adapter.in.web.dto.AnalyticsResponse;
import tech.buildrun.core.domain.LinkAnalytics;
import tech.buildrun.core.exception.FilterException;
import tech.buildrun.core.exception.LinkNotAllowedException;
import tech.buildrun.core.exception.LinkNotFoundException;
import tech.buildrun.core.port.in.LinkAnalyticsPortIn;
import tech.buildrun.core.port.out.AnalyticsRepositoryPortOut;
import tech.buildrun.core.port.out.LinkRepositoryPortOut;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class LinkAnalyticsUseCase implements LinkAnalyticsPortIn {

    private final LinkRepositoryPortOut linkRepositoryPortOut;
    private final AnalyticsRepositoryPortOut analyticsRepositoryPortOut;

    public LinkAnalyticsUseCase(LinkRepositoryPortOut linkRepositoryPortOut,
                                AnalyticsRepositoryPortOut analyticsRepositoryPortOut) {
        this.linkRepositoryPortOut = linkRepositoryPortOut;
        this.analyticsRepositoryPortOut = analyticsRepositoryPortOut;
    }

    @Override
    public AnalyticsResponse execute(String userId, String linkId, LocalDate startDate, LocalDate endDate) {

        validateRange(startDate, endDate);

        var link = linkRepositoryPortOut.findById(linkId)
                .orElseThrow(LinkNotFoundException::new);

        if (!link.isUserOwner(UUID.fromString(userId))) {
            throw new LinkNotAllowedException();
        }

        var linkAnalytics = analyticsRepositoryPortOut.findAll(linkId, startDate, endDate);

        var totalVisitors = getTotalVisitors(linkAnalytics);
        var analyticsPerDay = getAnalyticsPerDay(linkAnalytics);

        return new AnalyticsResponse(
                totalVisitors,
                analyticsPerDay
        );
    }

    private void validateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new FilterException(Map.of("startDate", "must be before endDate"));
        }
    }

    private List<AnalyticsDayResponse> getAnalyticsPerDay(List<LinkAnalytics> linkAnalytics) {
        return linkAnalytics.stream()
                .map(AnalyticsDayResponse::fromDomain)
                .toList();
    }

    private Long getTotalVisitors(List<LinkAnalytics> linkAnalytics) {
        return linkAnalytics.stream()
                .map(LinkAnalytics::getClicks)
                .reduce(0L, Long::sum);
    }
}
