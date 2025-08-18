package tech.buildrun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class AwsConfig {

    @Bean
    @Profile("local")
    public DynamoDbClient dynamoDbClientLocal() {
        return DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localstack-main:4566"))
                .region(Region.SA_EAST_1)
                .build();
    }

    @Bean
    @Profile("!local")
    public DynamoDbClient dynamoDbClientAws() {
        return DynamoDbClient.builder()
                .build();
    }

}
