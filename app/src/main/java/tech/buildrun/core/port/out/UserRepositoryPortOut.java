package tech.buildrun.core.port.out;

import tech.buildrun.core.domain.User;

import java.util.Optional;

public interface UserRepositoryPortOut {

    User save(User user);

    Optional<User> findByEmail(String email);
}
