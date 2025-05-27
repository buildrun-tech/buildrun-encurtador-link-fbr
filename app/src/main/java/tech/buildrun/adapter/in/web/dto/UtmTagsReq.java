package tech.buildrun.adapter.in.web.dto;

import tech.buildrun.core.domain.UtmTags;

public record UtmTagsReq(
        String source,
        String medium,
        String campaign,
        String content
) {
    public UtmTags toDomain() {
        return new UtmTags(
                this.source,
                this.medium,
                this.campaign,
                this.content
        );
    }
}
