package tech.buildrun.config.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JwtSecretPayload(
        @JsonProperty("public_key")
        String publicKey,
        @JsonProperty("private_key")
        String privateKey) {

}
