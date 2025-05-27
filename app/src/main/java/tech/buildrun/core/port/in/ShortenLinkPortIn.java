package tech.buildrun.core.port.in;

import tech.buildrun.adapter.in.web.dto.ShortenLinkResponse;
import tech.buildrun.core.domain.Link;

public interface ShortenLinkPortIn {

    ShortenLinkResponse execute(Link req);
}
