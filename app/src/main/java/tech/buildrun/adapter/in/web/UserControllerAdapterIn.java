package tech.buildrun.adapter.in.web;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.adapter.in.web.dto.CreateUserRequest;
import tech.buildrun.adapter.in.web.dto.CreateUserResponse;
import tech.buildrun.core.port.in.CreateUserPortIn;
import tech.buildrun.core.port.in.DeleteUserPortIn;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
@Validated
public class UserControllerAdapterIn {

    private final CreateUserPortIn createUserPortIn;
    private final DeleteUserPortIn deleteUserPortIn;

    public UserControllerAdapterIn(CreateUserPortIn createUserPortIn,
                                   DeleteUserPortIn deleteUserPortIn) {
        this.createUserPortIn = createUserPortIn;
        this.deleteUserPortIn = deleteUserPortIn;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest req) {

        var userCreated = createUserPortIn.execute(req.toDomain());

        var body = CreateUserResponse.fromDomain(userCreated);

        return ResponseEntity.created(URI.create("/")).body(body);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(JwtAuthenticationToken token) {

        var userId = String.valueOf(token.getTokenAttributes().get("sub"));

        deleteUserPortIn.execute(UUID.fromString(userId));

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<String> authRoute(JwtAuthenticationToken token) {

        var email = token.getTokenAttributes().get("email");

        return ResponseEntity.ok(String.valueOf(email));
    }
}
