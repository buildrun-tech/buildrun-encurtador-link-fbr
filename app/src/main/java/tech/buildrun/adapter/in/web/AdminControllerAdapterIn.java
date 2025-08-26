package tech.buildrun.adapter.in.web;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.buildrun.adapter.in.web.dto.CreateUserRequest;
import tech.buildrun.adapter.in.web.dto.CreateUserResponse;
import tech.buildrun.core.port.in.BootstrapAdminPortIn;

import java.net.URI;

@RestController
@RequestMapping(path = "/bootstrap-admin")
@Validated
public class AdminControllerAdapterIn {

    private final BootstrapAdminPortIn bootstrapAdminPortIn;

    public AdminControllerAdapterIn(BootstrapAdminPortIn bootstrapAdminPortIn) {
        this.bootstrapAdminPortIn = bootstrapAdminPortIn;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> bootstrapAdmin(@RequestBody @Valid CreateUserRequest req) {

        var userCreated = bootstrapAdminPortIn.execute(req.toDomain());

        var body = CreateUserResponse.fromDomain(userCreated);

        return ResponseEntity.created(URI.create("/")).body(body);
    }
}
