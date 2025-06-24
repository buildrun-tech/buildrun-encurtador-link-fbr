package tech.buildrun.core.port.in;

import tech.buildrun.core.domain.Link;
import tech.buildrun.core.domain.PaginatedResult;

public interface MyLinksPortIn {

    PaginatedResult<Link> execute(String uuid, String nextToken, int limit);
}
