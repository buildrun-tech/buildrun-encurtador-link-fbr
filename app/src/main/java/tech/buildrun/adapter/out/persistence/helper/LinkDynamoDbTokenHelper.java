package tech.buildrun.adapter.out.persistence.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import static tech.buildrun.adapter.out.persistence.DynamoDbAttributeConstants.LINK_ID;
import static tech.buildrun.adapter.out.persistence.DynamoDbAttributeConstants.LINK_USER_ID;

@Component
public class LinkDynamoDbTokenHelper {

    private final ObjectMapper mapper;

    public LinkDynamoDbTokenHelper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String encodeStartToken(Map<String, AttributeValue> key) {
        try {
            var dto = new TokenDto(key.get(LINK_ID).s(), key.get(LINK_USER_ID).s());

            String json = mapper.writeValueAsString(dto);

            return Base64.getEncoder()
                    .encodeToString(json.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            throw new RuntimeException("Failed to encode start token", e);
        }
    }

    public Map<String, AttributeValue> decodeStartToken(String token) {
        try {
            byte[] decoded = Base64.getDecoder().decode(token);

            String json = new String(decoded, StandardCharsets.UTF_8);

            var dto = mapper.readValue(json, TokenDto.class);

            return Map.of(
                LINK_USER_ID, AttributeValue.fromS(dto.userId()),
                LINK_ID, AttributeValue.fromS(dto.linkId())
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to decode start token", e);
        }
    }

}
