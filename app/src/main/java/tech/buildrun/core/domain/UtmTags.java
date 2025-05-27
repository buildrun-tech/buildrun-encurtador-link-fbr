package tech.buildrun.core.domain;

public class UtmTags {

    private String utmSource;
    private String utmMedium;
    private String utmCampaign;
    private String utmContent;

    public UtmTags(String utmSource, String utmMedium, String utmCampaign, String utmContent) {
        this.utmSource = utmSource;
        this.utmMedium = utmMedium;
        this.utmCampaign = utmCampaign;
        this.utmContent = utmContent;
    }

    public String getUtmSource() {
        return utmSource;
    }

    public String getUtmMedium() {
        return utmMedium;
    }

    public String getUtmCampaign() {
        return utmCampaign;
    }

    public String getUtmContent() {
        return utmContent;
    }
}
