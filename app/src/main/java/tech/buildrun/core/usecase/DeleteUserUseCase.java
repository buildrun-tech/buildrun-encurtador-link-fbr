package tech.buildrun.core.usecase;

import org.springframework.stereotype.Component;
import tech.buildrun.core.exception.UserNotFoundException;
import tech.buildrun.core.port.in.DeleteUserPortIn;
import tech.buildrun.core.port.out.UserRepositoryPortOut;

import java.util.UUID;

@Component
public class DeleteUserUseCase implements DeleteUserPortIn {

    private final UserRepositoryPortOut userRepository;

    public DeleteUserUseCase(UserRepositoryPortOut userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(UUID userId) {

        userRepository.findById(userId)
                        .orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(userId);
    }
}
