package tech.buildrun.core.port.out;

import tech.buildrun.core.domain.Link;
import tech.buildrun.core.domain.PaginatedResult;

import java.util.Optional;

public interface LinkRepositoryPortOut {

    Link save(Link link);
    Optional<Link> findById(String id);
    PaginatedResult<Link> findAllByUserId(String userId, String nextToken, int limit);
}
