package tech.buildrun.core.port.out;

import tech.buildrun.core.domain.Link;

public interface AnalyticsRepositoryPortOut {

    void updateClickCount(Link link);
}
