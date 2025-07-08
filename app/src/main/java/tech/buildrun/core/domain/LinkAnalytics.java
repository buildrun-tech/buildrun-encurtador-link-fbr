package tech.buildrun.core.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LinkAnalytics {

    private String linkId;
    private LocalDate date;
    private Long clicks;
    private LocalDateTime updatedAt;

    public LinkAnalytics(String linkId, LocalDate date, Long clicks, LocalDateTime updatedAt) {
        this.linkId = linkId;
        this.date = date;
        this.clicks = clicks;
        this.updatedAt = updatedAt;
    }

    public String getLinkId() {
        return linkId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getClicks() {
        return clicks;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
