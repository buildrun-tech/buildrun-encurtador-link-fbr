package tech.buildrun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class AwsJwtSecretConfig {

    @Value("${jwt.app.pub}")
    private String publicPem;

    @Value("${jwt.app.key}")
    private String privatePem;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expires.in}")
    private Long expiresIn;

    @Bean
    public RSAPublicKey jwtPublicKey() throws Exception {
        String content = publicPem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        byte[] der = Base64.getDecoder().decode(content);
        var spec = new X509EncodedKeySpec(der);
        var kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(spec);
    }

    @Bean
    public RSAPrivateKey jwtPrivateKey() throws Exception {
        String content = privatePem
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        byte[] der = Base64.getDecoder().decode(content);
        var spec = new PKCS8EncodedKeySpec(der);
        var kf = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) kf.generatePrivate(spec);
    }

    @Bean
    public String jwtIssuer() { return issuer; }

    @Bean
    public Long jwtExpiresIn() { return expiresIn; }
}

