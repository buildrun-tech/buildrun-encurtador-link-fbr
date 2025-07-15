package tech.buildrun.adapter.out.persistence;

public class DynamoDbAttributeConstants {

    // LinkEntity
    public static final String LINK_ID = "link_id";
    public static final String LINK_ORIGINAL_URL = "original_url";
    public static final String LINK_UTM_SOURCE = "utm_source";
    public static final String LINK_UTM_MEDIUM = "utm_medium";
    public static final String LINK_UTM_CAMPAIGN = "utm_campaign";
    public static final String LINK_UTM_CONTENT = "utm_content";
    public static final String LINK_USER_ID = "user_id";
    public static final String LINK_ACTIVE = "active";
    public static final String LINK_EXPIRATION_DATE_TIME = "expiration_date_time";
    public static final String LINK_CREATED_AT = "created_at";
    public static final String LINK_UPDATED_AT = "updated_at";

    // LinkAnalyticsEntity
    public static final String ANALYTICS_LINK_ID = "link_id";
    public static final String ANALYTICS_DATE = "date";
    public static final String ANALYTICS_CLICKS = "clicks";
    public static final String ANALYTICS_UPDATED_AT = "updated_at";

    // UserEntity
    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_NICKNAME = "nickname";
    public static final String USER_CREATED_AT = "created_at";
    public static final String USER_UPDATED_AT = "updated_at";

}
