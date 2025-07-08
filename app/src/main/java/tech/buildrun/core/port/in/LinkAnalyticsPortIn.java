package tech.buildrun.core.port.in;

import tech.buildrun.adapter.in.web.dto.AnalyticsResponse;

import java.time.LocalDate;

public interface LinkAnalyticsPortIn {

    AnalyticsResponse execute(String userId,
                              String linkId,
                              LocalDate startDate,
                              LocalDate endDate);
}
