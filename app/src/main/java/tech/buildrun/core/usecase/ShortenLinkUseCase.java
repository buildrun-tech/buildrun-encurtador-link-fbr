package tech.buildrun.core.usecase;

import org.springframework.stereotype.Component;
import tech.buildrun.adapter.in.web.dto.ShortenLinkResponse;
import tech.buildrun.core.domain.Link;
import tech.buildrun.core.port.in.ShortenLinkPortIn;
import tech.buildrun.core.port.out.LinkRepositoryPortOut;

@Component
public class ShortenLinkUseCase implements ShortenLinkPortIn {

    private final LinkRepositoryPortOut linkRepositoryPortOut;

    public ShortenLinkUseCase(LinkRepositoryPortOut linkRepositoryPortOut) {
        this.linkRepositoryPortOut = linkRepositoryPortOut;
    }

    @Override
    public ShortenLinkResponse execute(Link link) {

        // TODO - validar se ja existe um link pelo ID

        linkRepositoryPortOut.save(link);

        return new ShortenLinkResponse("http://localhost:3000/" + link.getLinkId());
    }
}
