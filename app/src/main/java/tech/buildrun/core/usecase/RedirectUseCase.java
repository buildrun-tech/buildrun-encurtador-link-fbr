package tech.buildrun.core.usecase;

import org.springframework.stereotype.Component;
import tech.buildrun.core.exception.LinkNotFoundException;
import tech.buildrun.core.port.in.RedirectPortIn;
import tech.buildrun.core.port.out.AnalyticsRepositoryPortOut;
import tech.buildrun.core.port.out.LinkRepositoryPortOut;

import java.time.LocalDateTime;

@Component
public class RedirectUseCase implements RedirectPortIn {

    private final LinkRepositoryPortOut linkRepositoryPortOut;
    private final AnalyticsRepositoryPortOut analyticsRepositoryPortOut;

    public RedirectUseCase(LinkRepositoryPortOut linkRepositoryPortOut,
                           AnalyticsRepositoryPortOut analyticsRepositoryPortOut) {
        this.linkRepositoryPortOut = linkRepositoryPortOut;
        this.analyticsRepositoryPortOut = analyticsRepositoryPortOut;
    }

    @Override
    public String execute(String linkId) {

        var link = linkRepositoryPortOut.findById(linkId)
                .orElseThrow(LinkNotFoundException::new);

        if (!link.isActive()) {
            throw new LinkNotFoundException();
        }

        if (link.getExpirationDateTime().isBefore(LocalDateTime.now())) {
            throw new LinkNotFoundException();
        }

        analyticsRepositoryPortOut.updateClickCount(link);

        return link.generateFullUrl();
    }
}
