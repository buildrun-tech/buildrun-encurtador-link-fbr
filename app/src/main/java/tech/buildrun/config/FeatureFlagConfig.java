package tech.buildrun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeatureFlagConfig {

    @Value("${app.create-users.enabled}")
    public Boolean createUsersEnabled;

    public Boolean getCreateUsersEnabled() {
        return createUsersEnabled;
    }
}
