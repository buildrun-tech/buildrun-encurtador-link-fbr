package tech.buildrun.adapter.out.persistence;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import tech.buildrun.core.domain.User;
import tech.buildrun.core.port.out.UserRepositoryPortOut;

import java.util.Optional;
import java.util.UUID;

import static tech.buildrun.config.Constants.EMAIL_INDEX;

@Component
public class UserDynamoDbAdapterOut implements UserRepositoryPortOut {

    private final Logger logger = LoggerFactory.getLogger(UserDynamoDbAdapterOut.class);

    private final DynamoDbTemplate dynamoDbTemplate;

    public UserDynamoDbAdapterOut(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @Override
    public User save(User user) {

        logger.debug("Start save user on DynamoDb... - {}", user);

        var entity = UserEntity.fromDomain(user);

        dynamoDbTemplate.save(entity);

        logger.debug("End save user on DynamoDb! - {}", user);

        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {

        logger.debug("Start FindUserbyEmail on DynamoDb... - {}", email);

        var cond = QueryConditional.keyEqualTo(k ->
                k.partitionValue(AttributeValue.builder().s(email).build())
        );

        var query = QueryEnhancedRequest.builder()
                .queryConditional(cond)
                .build();

        var result = dynamoDbTemplate.query(query, UserEntity.class, EMAIL_INDEX);

        var opt = result.stream()
                .flatMap(userEntityPage -> userEntityPage.items().stream())
                .map(UserEntity::toDomain)
                .findFirst();

        logger.debug("End FindUserbyEmail on DynamoDb! - {}", email);

        return opt;
    }

    @Override
    public void deleteById(UUID userId) {

        logger.debug("Start DeleteById on DynamoDb... - {}", userId);

        var key = Key.builder()
                .partitionValue(userId.toString())
                .build();

        dynamoDbTemplate.delete(key, UserEntity.class);

        logger.debug("End DeleteById on DynamoDb... - {}", userId);
    }

    @Override
    public Optional<User> findById(UUID userId) {

        logger.debug("Start FindUserById on DynamoDb... - {}", userId);

        var key = Key.builder()
                .partitionValue(userId.toString())
                .build();

        var entity = dynamoDbTemplate.load(key, UserEntity.class);

        Optional<User> opt = entity == null ?
                Optional.empty() :
                Optional.of(entity.toDomain());

        logger.debug("End FindUserById on DynamoDb... - {}", userId);

        return opt;
    }
}
