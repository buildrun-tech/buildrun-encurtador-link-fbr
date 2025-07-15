package tech.buildrun.adapter.out.persistence;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import tech.buildrun.adapter.out.persistence.helper.LinkDynamoDbTokenHelper;
import tech.buildrun.core.domain.Link;
import tech.buildrun.core.domain.LinkFilter;
import tech.buildrun.core.domain.PaginatedResult;
import tech.buildrun.core.port.out.LinkRepositoryPortOut;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static tech.buildrun.adapter.out.persistence.DynamoDbAttributeConstants.LINK_ACTIVE;
import static tech.buildrun.adapter.out.persistence.DynamoDbAttributeConstants.LINK_CREATED_AT;
import static tech.buildrun.config.Constants.FK_TB_USERS_LINK_USER_INDEX;

@Component
public class LinkDynamoDbAdapterOut implements LinkRepositoryPortOut {

    private final DynamoDbTemplate dynamoDbTemplate;
    private final LinkDynamoDbTokenHelper tokenHelper;

    public LinkDynamoDbAdapterOut(DynamoDbTemplate dynamoDbTemplate,
                                  LinkDynamoDbTokenHelper tokenHelper) {
        this.dynamoDbTemplate = dynamoDbTemplate;
        this.tokenHelper = tokenHelper;
    }

    @Override
    public Link save(Link link) {

        var entity = LinkEntity.fromDomain(link);

        dynamoDbTemplate.save(entity);

        return link;
    }

    @Override
    public Optional<Link> findById(String id) {

        var key = Key.builder()
                .partitionValue(id)
                .build();

        var entity = dynamoDbTemplate.load(key, LinkEntity.class);

        return entity == null ?
                Optional.empty() :
                Optional.of(entity.toDomain());
    }

    @Override
    public PaginatedResult<Link> findAllByUserId(String userId,
                                                 String nextToken,
                                                 int limit,
                                                 LinkFilter filters) {

        QueryConditional qc = buildPartitionKeyUserId(userId);

        List<String> conditions = new ArrayList<>();
        Map<String, AttributeValue> expValues = new HashMap<>();

        buildFiltersParam(filters, conditions, expValues);

        var queryReq = buildDynamoDbRequest(
                nextToken, limit, qc, conditions, expValues
        );

        Page<LinkEntity> page = executeQuery(queryReq);

        return convertAndReturn(page);
    }

    private static QueryConditional buildPartitionKeyUserId(String userId) {
        return QueryConditional.keyEqualTo(
                Key.builder()
                        .partitionValue(userId)
                        .build()
        );
    }

    private static void buildFiltersParam(LinkFilter filters, List<String> conditions, Map<String, AttributeValue> expValues) {
        if (!isNull(filters.active())) {
            conditions.add(format("%s = :activeValue", LINK_ACTIVE));
            expValues.put(":activeValue", AttributeValue.fromBool(filters.active()));
        }

        if (!isNull(filters.startCreatedAt()) && !isNull(filters.endCreatedAt())) {
            conditions.add(format("%s BETWEEN :startCreatedAt AND :endCreatedAt", LINK_CREATED_AT));
            expValues.put(":startCreatedAt", AttributeValue.fromS(LocalDateTime.of(filters.startCreatedAt(), LocalTime.MIN).toString()));
            expValues.put(":endCreatedAt", AttributeValue.fromS(LocalDateTime.of(filters.endCreatedAt(), LocalTime.MAX).toString()));
        }
    }

    private QueryEnhancedRequest buildDynamoDbRequest(String nextToken, int limit, QueryConditional qc, List<String> conditions, Map<String, AttributeValue> expValues) {
        QueryEnhancedRequest.Builder requestBuilder = QueryEnhancedRequest.builder()
                .queryConditional(qc)
                .limit(limit);

        if (!conditions.isEmpty()) {
            requestBuilder.filterExpression(Expression.builder()
                            .expression(String.join(" AND ", conditions))
                            .expressionValues(expValues)
                            .build());
        }

        if (nextToken != null && !nextToken.isEmpty()) {
            var map = tokenHelper.decodeStartToken(nextToken);
            requestBuilder.exclusiveStartKey(map);
        }

        return requestBuilder.build();
    }

    private Page<LinkEntity> executeQuery(QueryEnhancedRequest queryReq) {
        return dynamoDbTemplate
                .query(queryReq, LinkEntity.class, FK_TB_USERS_LINK_USER_INDEX)
                .stream()
                .findFirst()
                .orElse(null);
    }

    private PaginatedResult<Link> convertAndReturn(Page<LinkEntity> page) {
        if (page == null) {
            return new PaginatedResult<>(Collections.emptyList(), null, false);
        }

        var links = page.items()
                .stream()
                .map(LinkEntity::toDomain)
                .collect(Collectors.toList());

        return new PaginatedResult<>(
                links,
                page.lastEvaluatedKey() != null ? tokenHelper.encodeStartToken(page.lastEvaluatedKey()) : "",
                page.lastEvaluatedKey() != null
        );
    }

}
