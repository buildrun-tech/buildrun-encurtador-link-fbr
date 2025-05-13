package tech.buildrun.core.port.in;

import tech.buildrun.adapter.in.web.dto.LoginRequest;
import tech.buildrun.adapter.in.web.dto.LoginResponse;

public interface AuthnPortIn {

    LoginResponse execute(LoginRequest req);
}
