package tech.buildrun.core.port.out;

import tech.buildrun.core.domain.Link;

public interface LinkMessagingPortOut {

    void publishUpdateLinkCount(Link link);
}
