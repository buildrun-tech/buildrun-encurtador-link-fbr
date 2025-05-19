package tech.buildrun.adapter.in.web;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.adapter.in.web.dto.CreateUserRequest;
import tech.buildrun.adapter.in.web.dto.CreateUserResponse;
import tech.buildrun.core.port.in.CreateUserPortIn;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
@Validated
public class UserControllerAdapterIn {

    private final CreateUserPortIn createUserPortIn;

    public UserControllerAdapterIn(CreateUserPortIn createUserPortIn) {
        this.createUserPortIn = createUserPortIn;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest req) {

        var userCreated = createUserPortIn.execute(req.toDomain());

        var body = CreateUserResponse.fromDomain(userCreated);

        return ResponseEntity.created(URI.create("/")).body(body);
    }

    @GetMapping
    public ResponseEntity<String> authRoute(JwtAuthenticationToken token) {

        var email = token.getTokenAttributes().get("email");

        return ResponseEntity.ok(String.valueOf(email));
    }
}
