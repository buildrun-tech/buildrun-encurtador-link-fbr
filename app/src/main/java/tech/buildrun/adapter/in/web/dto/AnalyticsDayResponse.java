package tech.buildrun.adapter.in.web.dto;

import tech.buildrun.core.domain.LinkAnalytics;

import java.time.LocalDate;

public record AnalyticsDayResponse(LocalDate date,
                                   Long totalVisitors) {

    public static AnalyticsDayResponse fromDomain(LinkAnalytics linkAnalytics) {
        return new AnalyticsDayResponse(linkAnalytics.getDate(), linkAnalytics.getClicks());
    }
}
