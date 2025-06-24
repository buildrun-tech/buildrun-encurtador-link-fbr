package tech.buildrun.core.usecase;

import org.springframework.stereotype.Component;
import tech.buildrun.core.domain.Link;
import tech.buildrun.core.domain.PaginatedResult;
import tech.buildrun.core.port.in.MyLinksPortIn;
import tech.buildrun.core.port.out.LinkRepositoryPortOut;

@Component
public class MyLinksUseCase implements MyLinksPortIn {

    private final LinkRepositoryPortOut linkRepositoryPortOut;

    public MyLinksUseCase(LinkRepositoryPortOut linkRepositoryPortOut) {
        this.linkRepositoryPortOut = linkRepositoryPortOut;
    }

    @Override
    public PaginatedResult<Link> execute(String userId,
                                         String nextToken,
                                         int limit) {

        return linkRepositoryPortOut.findAllByUserId(userId,
                nextToken,
                limit);
    }
}
