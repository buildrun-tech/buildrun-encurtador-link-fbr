package tech.buildrun.config;

import io.awspring.cloud.dynamodb.DynamoDbTableNameResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomTableNameResolver implements DynamoDbTableNameResolver {

    @Value("${ENV}")
    private String env;

    @Override
    public <T> String resolve(Class<T> clazz) {
        return env + "_" + clazz.getAnnotation(TableName.class).name();
    }
}
