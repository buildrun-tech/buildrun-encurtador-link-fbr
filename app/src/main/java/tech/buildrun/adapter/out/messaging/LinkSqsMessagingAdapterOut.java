package tech.buildrun.adapter.out.messaging;

import org.springframework.stereotype.Component;
import tech.buildrun.core.domain.Link;
import tech.buildrun.core.port.out.LinkMessagingPortOut;

@Component
public class LinkSqsMessagingAdapterOut implements LinkMessagingPortOut {

    @Override
    public void publishUpdateLinkCount(Link link) {
        // TODO - publicar para o SQS
        // TODO - consumir do sqs via lambda
    }
}
