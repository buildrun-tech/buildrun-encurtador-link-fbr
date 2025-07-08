package tech.buildrun.core.port.out;

import tech.buildrun.core.domain.Link;
import tech.buildrun.core.domain.LinkAnalytics;

import java.time.LocalDate;
import java.util.List;

public interface AnalyticsRepositoryPortOut {

    void updateClickCount(Link link);

    List<LinkAnalytics> findAll(String linkId, LocalDate startDate, LocalDate endDate);
}
