package tech.buildrun.adapter.in.web.dto;

import tech.buildrun.core.domain.Link;

import java.time.LocalDateTime;

public record LinkResponse(
        String linkId,
        String originalUrl,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static LinkResponse fromDomain(Link link) {
        return new LinkResponse(
                link.getLinkId(),
                link.getOriginalUrl(),
                link.isActive(),
                link.getCreatedAt(),
                link.getUpdatedAt()
        );
    }
}
