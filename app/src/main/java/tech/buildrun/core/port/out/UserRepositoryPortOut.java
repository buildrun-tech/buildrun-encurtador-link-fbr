package tech.buildrun.core.port.out;

import tech.buildrun.core.domain.User;

public interface UserRepositoryPortOut {

    User save(User user);
}
