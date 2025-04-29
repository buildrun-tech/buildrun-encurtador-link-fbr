package tech.buildrun.core.port.in;

import tech.buildrun.core.domain.User;

public interface CreateUserPortIn {

    User execute(User user);
}
