package tech.buildrun.core.port.in;

import tech.buildrun.core.domain.User;

public interface BootstrapAdminPortIn {

    User execute(User user);
}
