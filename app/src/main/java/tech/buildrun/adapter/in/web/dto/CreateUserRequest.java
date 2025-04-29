package tech.buildrun.adapter.in.web.dto;

import tech.buildrun.core.domain.User;

public record CreateUserRequest(String email,
                                String password,
                                String nickname) {

    public User toDomain() {
        return new User(email, password, nickname);
    }
}
