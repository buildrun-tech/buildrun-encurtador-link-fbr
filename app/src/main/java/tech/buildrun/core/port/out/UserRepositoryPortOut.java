package tech.buildrun.core.port.out;

import tech.buildrun.core.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPortOut {

    User save(User user);

    Optional<User> findByEmail(String email);

    void deleteById(UUID userId);

    Optional<User> findById(UUID userId);
}
