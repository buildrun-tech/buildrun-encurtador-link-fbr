package tech.buildrun.core.usecase;

import org.springframework.stereotype.Component;
import tech.buildrun.core.exception.LinkNotFoundException;
import tech.buildrun.core.port.in.RedirectPortIn;
import tech.buildrun.core.port.out.LinkMessagingPortOut;
import tech.buildrun.core.port.out.LinkRepositoryPortOut;

@Component
public class RedirectUseCase implements RedirectPortIn {

    private final LinkRepositoryPortOut linkRepositoryPortOut;
    private final LinkMessagingPortOut linkMessagingPortOut;

    public RedirectUseCase(LinkRepositoryPortOut linkRepositoryPortOut,
                           LinkMessagingPortOut linkMessagingPortOut) {
        this.linkRepositoryPortOut = linkRepositoryPortOut;
        this.linkMessagingPortOut = linkMessagingPortOut;
    }

    @Override
    public String execute(String linkId) {

        var link = linkRepositoryPortOut.findById(linkId)
                .orElseThrow(LinkNotFoundException::new);

        linkMessagingPortOut.publishUpdateLinkCount(link);

        return link.generateFullUrl();
    }
}
