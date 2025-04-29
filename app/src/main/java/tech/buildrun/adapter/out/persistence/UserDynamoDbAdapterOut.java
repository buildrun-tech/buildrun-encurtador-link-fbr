package tech.buildrun.adapter.out.persistence;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.stereotype.Component;
import tech.buildrun.core.domain.User;
import tech.buildrun.core.port.out.UserRepositoryPortOut;

@Component
public class UserDynamoDbAdapterOut implements UserRepositoryPortOut {

    private final DynamoDbTemplate dynamoDbTemplate;

    public UserDynamoDbAdapterOut(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @Override
    public User save(User user) {
        var entity = UserEntity.fromDomain(user);

        dynamoDbTemplate.save(entity);

        return user;
    }
}
