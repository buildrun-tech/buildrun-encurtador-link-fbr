package tech.buildrun.core.usecase;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Component;
import tech.buildrun.adapter.in.web.dto.LoginRequest;
import tech.buildrun.adapter.in.web.dto.LoginResponse;
import tech.buildrun.core.port.in.AuthnPortIn;
import tech.buildrun.core.port.out.UserRepositoryPortOut;

@Component
public class AuthnUseCase implements AuthnPortIn {

    private final UserRepositoryPortOut userRepository;
    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthnUseCase(UserRepositoryPortOut userRepository,
                        JwtEncoder jwtEncoder,
                        BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.jwtEncoder = jwtEncoder;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public LoginResponse execute(LoginRequest req) {

        // find by username
        // find by user_id
        // comparar a senha
        // se a senha for valida
        //      gera o token JWT
        // se a senha nao for valida
        //      retorna erro de credenciais invalida

        return null;
    }
}
