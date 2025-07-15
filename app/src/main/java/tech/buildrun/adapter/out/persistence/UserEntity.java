package tech.buildrun.adapter.out.persistence;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import tech.buildrun.config.TableName;
import tech.buildrun.core.domain.User;

import java.time.LocalDateTime;
import java.util.UUID;

import static tech.buildrun.adapter.out.persistence.DynamoDbAttributeConstants.*;
import static tech.buildrun.config.Constants.EMAIL_INDEX;

@DynamoDbBean
@TableName(name = "tb_users")
public class UserEntity {

    private UUID userId;
    private String email;
    private String password;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserEntity() {
    }

    public static UserEntity fromDomain(User user) {
        var entity = new UserEntity();

        entity.setUserId(user.getUserId());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setNickname(user.getNickname());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());

        return entity;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute(USER_ID)
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = EMAIL_INDEX)
    @DynamoDbAttribute(USER_EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDbAttribute(USER_PASSWORD)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDbAttribute(USER_NICKNAME)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @DynamoDbAttribute(USER_CREATED_AT)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @DynamoDbAttribute(USER_UPDATED_AT)
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User toDomain() {
        return new User(
                this.userId,
                this.email,
                this.password,
                this.nickname,
                this.createdAt,
                this.updatedAt
        );
    }
}
