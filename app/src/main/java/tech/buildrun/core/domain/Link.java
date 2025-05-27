package tech.buildrun.core.domain;

import java.time.LocalDateTime;

public class Link {

    private String linkId;
    private String originalUrl;
    private UtmTags utmTags;
    private User user;

    private boolean active;
    private LocalDateTime expirationDateTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Link(String linkId, String originalUrl, UtmTags utmTags, User user, boolean active, LocalDateTime expirationDateTime, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.linkId = linkId;
        this.originalUrl = originalUrl;
        this.utmTags = utmTags;
        this.user = user;
        this.active = active;
        this.expirationDateTime = expirationDateTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getLinkId() {
        return linkId;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public UtmTags getUtmTags() {
        return utmTags;
    }

    public User getUser() {
        return user;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
