package tech.buildrun.adapter.out.persistence;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.stereotype.Component;
import tech.buildrun.core.domain.Link;
import tech.buildrun.core.port.out.LinkRepositoryPortOut;

@Component
public class LinkDynamoDbAdapterOut implements LinkRepositoryPortOut {

    private final DynamoDbTemplate dynamoDbTemplate;

    public LinkDynamoDbAdapterOut(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @Override
    public Link save(Link link) {

        var entity = LinkEntity.fromDomain(link);

        dynamoDbTemplate.save(entity);

        return link;
    }
}
