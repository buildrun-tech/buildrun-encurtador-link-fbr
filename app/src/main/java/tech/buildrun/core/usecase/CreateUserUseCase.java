package tech.buildrun.core.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tech.buildrun.core.domain.User;
import tech.buildrun.core.exception.UserAlreadyExistException;
import tech.buildrun.core.port.in.CreateUserPortIn;
import tech.buildrun.core.port.out.UserRepositoryPortOut;

@Component
public class CreateUserUseCase implements CreateUserPortIn {

    private static final Logger logger = LoggerFactory.getLogger(CreateUserUseCase.class);

    private final UserRepositoryPortOut userRepositoryPortOut;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CreateUserUseCase(UserRepositoryPortOut userRepositoryPortOut,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepositoryPortOut = userRepositoryPortOut;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User execute(User user) {

        logger.info("Creating user {}", user.getEmail());

        var optUser = userRepositoryPortOut.findByEmail(user.getEmail());

        if (optUser.isPresent()) {
            throw new UserAlreadyExistException();
        }

        user.encodePassword(bCryptPasswordEncoder);

        var userCreated = userRepositoryPortOut.save(user);

        logger.info("User created {}", user.getUserId());

        return userCreated;
    }
}
