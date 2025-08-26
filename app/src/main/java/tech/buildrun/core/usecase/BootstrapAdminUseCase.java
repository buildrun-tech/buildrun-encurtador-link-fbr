package tech.buildrun.core.usecase;

import org.springframework.stereotype.Component;
import tech.buildrun.config.FeatureFlagConfig;
import tech.buildrun.core.domain.User;
import tech.buildrun.core.exception.ResourceNotAvailableException;
import tech.buildrun.core.port.in.BootstrapAdminPortIn;
import tech.buildrun.core.port.out.UserRepositoryPortOut;

@Component
public class BootstrapAdminUseCase implements BootstrapAdminPortIn {


    private final UserRepositoryPortOut userRepositoryPortOut;
    private final CreateUserUseCase createUserUseCase;
    private final FeatureFlagConfig featureFlagConfig;

    public BootstrapAdminUseCase(UserRepositoryPortOut userRepositoryPortOut,
                                 CreateUserUseCase createUserUseCase,
                                 FeatureFlagConfig featureFlagConfig) {
        this.userRepositoryPortOut = userRepositoryPortOut;
        this.createUserUseCase = createUserUseCase;
        this.featureFlagConfig = featureFlagConfig;
    }

    @Override
    public User execute(User user) {

        if (featureFlagConfig.getCreateUsersEnabled()) {
            throw new ResourceNotAvailableException();
        }

        if (userRepositoryPortOut.count() != 0) {
            throw new ResourceNotAvailableException();
        }

        return createUserUseCase.execute(user);
    }
}
