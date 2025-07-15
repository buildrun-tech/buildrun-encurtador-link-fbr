package tech.buildrun.core.usecase;

import org.springframework.stereotype.Component;
import tech.buildrun.core.domain.Link;
import tech.buildrun.core.domain.LinkFilter;
import tech.buildrun.core.domain.PaginatedResult;
import tech.buildrun.core.port.in.UserLinksPortIn;
import tech.buildrun.core.port.out.LinkRepositoryPortOut;

@Component
public class UserLinksUseCase implements UserLinksPortIn {

    private final LinkRepositoryPortOut linkRepositoryPortOut;

    public UserLinksUseCase(LinkRepositoryPortOut linkRepositoryPortOut) {
        this.linkRepositoryPortOut = linkRepositoryPortOut;
    }

    @Override
    public PaginatedResult<Link> execute(String userId,
                                         String nextToken,
                                         int limit,
                                         LinkFilter filters) {

        filters.validate();

        return linkRepositoryPortOut.findAllByUserId(
                userId,
                nextToken,
                limit,
                filters
        );
    }
}
