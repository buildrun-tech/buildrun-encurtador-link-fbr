package tech.buildrun.core.port.in;

import tech.buildrun.core.domain.Link;
import tech.buildrun.core.domain.LinkFilter;
import tech.buildrun.core.domain.PaginatedResult;

public interface UserLinksPortIn {

    PaginatedResult<Link> execute(String uuid,
                                  String nextToken,
                                  int limit,
                                  LinkFilter filters);
}
