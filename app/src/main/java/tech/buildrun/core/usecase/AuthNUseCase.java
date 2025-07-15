package tech.buildrun.core.usecase;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import tech.buildrun.adapter.in.web.dto.LoginRequest;
import tech.buildrun.adapter.in.web.dto.LoginResponse;
import tech.buildrun.config.JwtConfig;
import tech.buildrun.core.exception.LoginException;
import tech.buildrun.core.port.in.AuthnPortIn;
import tech.buildrun.core.port.out.UserRepositoryPortOut;

import java.time.Instant;

import static tech.buildrun.config.Constants.JWT_EMAIL_CLAIM;

@Component
public class AuthNUseCase implements AuthnPortIn {

    private final UserRepositoryPortOut userRepository;
    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtConfig jwtConfig;

    public AuthNUseCase(UserRepositoryPortOut userRepository,
                        JwtEncoder jwtEncoder,
                        BCryptPasswordEncoder bCryptPasswordEncoder,
                        JwtConfig jwtConfig) {
        this.userRepository = userRepository;
        this.jwtEncoder = jwtEncoder;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public LoginResponse execute(LoginRequest req) {

        var user = this.userRepository.findByEmail(req.email())
                .orElseThrow(LoginException::new);

        var isPasswordValid = bCryptPasswordEncoder.matches(req.password(), user.getPassword());

        if (!isPasswordValid) {
            throw new LoginException();
        }

        var expiresIn = jwtConfig.getExpiresIn();

        var claims = JwtClaimsSet.builder()
                        .subject(user.getUserId().toString())
                        .issuer(jwtConfig.getIssuer())
                        .claim(JWT_EMAIL_CLAIM, user.getEmail())
                        .expiresAt(Instant.now().plusSeconds(expiresIn))
                        .build();

        var tokenJwt = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(tokenJwt, expiresIn);
    }
}
