package tech.buildrun.core.usecase;

import org.springframework.stereotype.Component;
import tech.buildrun.core.exception.LinkNotFoundException;
import tech.buildrun.core.port.in.RedirectPortIn;
import tech.buildrun.core.port.out.LinkRepositoryPortOut;

@Component
public class RedirectUseCase implements RedirectPortIn {

    private final LinkRepositoryPortOut linkRepositoryPortOut;

    public RedirectUseCase(LinkRepositoryPortOut linkRepositoryPortOut) {
        this.linkRepositoryPortOut = linkRepositoryPortOut;
    }

    @Override
    public String execute(String linkId) {

        var link = linkRepositoryPortOut.findById(linkId)
                .orElseThrow(LinkNotFoundException::new);

        return link.generateFullUrl();
    }
}
