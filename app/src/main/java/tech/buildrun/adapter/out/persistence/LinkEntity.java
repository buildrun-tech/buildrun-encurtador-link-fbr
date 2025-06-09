package tech.buildrun.adapter.out.persistence;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;
import tech.buildrun.config.TableName;
import tech.buildrun.core.domain.Link;
import tech.buildrun.core.domain.User;
import tech.buildrun.core.domain.UtmTags;

import java.time.LocalDateTime;
import java.util.UUID;

import static tech.buildrun.config.Constants.FK_TB_USERS_LINK_USER_INDEX;

@DynamoDbBean
@TableName(name = "tb_user_links")
public class LinkEntity {

    private String linkId;
    private String originalUrl;

    private String utmSource;
    private String utmMedium;
    private String utmCampaign;
    private String utmContent;

    private UUID userId;
    private boolean active;
    private LocalDateTime expirationDateTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LinkEntity() {
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("link_id")
    @DynamoDbSecondarySortKey(indexNames = FK_TB_USERS_LINK_USER_INDEX)
    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    @DynamoDbAttribute("original_url")
    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    @DynamoDbAttribute("utm_source")
    public String getUtmSource() {
        return utmSource;
    }

    public void setUtmSource(String utmSource) {
        this.utmSource = utmSource;
    }

    @DynamoDbAttribute("utm_medium")
    public String getUtmMedium() {
        return utmMedium;
    }

    public void setUtmMedium(String utmMedium) {
        this.utmMedium = utmMedium;
    }

    @DynamoDbAttribute("utm_campaign")
    public String getUtmCampaign() {
        return utmCampaign;
    }

    public void setUtmCampaign(String utmCampaign) {
        this.utmCampaign = utmCampaign;
    }

    @DynamoDbAttribute("utm_content")
    public String getUtmContent() {
        return utmContent;
    }

    public void setUtmContent(String utmContent) {
        this.utmContent = utmContent;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = FK_TB_USERS_LINK_USER_INDEX)
    @DynamoDbAttribute("user_id")
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @DynamoDbAttribute("active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @DynamoDbAttribute("expiration_date_time")
    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    @DynamoDbAttribute("created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @DynamoDbAttribute("updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static LinkEntity fromDomain(Link link) {
        var entity = new LinkEntity();

        entity.setLinkId(link.getLinkId());
        entity.setOriginalUrl(link.getOriginalUrl());

        if (link.getUtmTags() != null) {
            entity.setUtmSource(link.getUtmTags().getUtmSource());
            entity.setUtmMedium(link.getUtmTags().getUtmMedium());
            entity.setUtmCampaign(link.getUtmTags().getUtmCampaign());
            entity.setUtmContent(link.getUtmTags().getUtmContent());
        }

        entity.setUserId(link.getUser().getUserId());
        entity.setActive(link.isActive());
        entity.setExpirationDateTime(link.getExpirationDateTime());

        entity.setCreatedAt(link.getCreatedAt());
        entity.setUpdatedAt(link.getUpdatedAt());

        return entity;
    }

    public Link toDomain() {
        return new Link(
                linkId,
                originalUrl,
                new UtmTags(utmSource, utmMedium, utmCampaign, utmContent),
                new User(userId),
                active,
                expirationDateTime,
                createdAt,
                updatedAt
        );
    }
}
