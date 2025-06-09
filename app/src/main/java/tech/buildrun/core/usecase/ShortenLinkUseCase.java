package tech.buildrun.core.usecase;

import org.springframework.stereotype.Component;
import tech.buildrun.adapter.in.web.dto.ShortenLinkResponse;
import tech.buildrun.core.domain.Link;
import tech.buildrun.core.exception.LinkAlreadyExistException;
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

        var linkOpt = linkRepositoryPortOut.findById(link.getLinkId());

        if (linkOpt.isPresent()) {
            throw new LinkAlreadyExistException();
        }

        linkRepositoryPortOut.save(link);

        return new ShortenLinkResponse("http://localhost:3000/" + link.getLinkId());
    }
}
