package tech.buildrun.core.port.in;

import tech.buildrun.core.domain.Link;

public interface ShortenLinkPortIn {

    String execute(Link req);
}
