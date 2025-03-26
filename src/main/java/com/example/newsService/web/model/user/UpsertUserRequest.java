package com.example.newsService.web.model.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertUserRequest {

    @NotNull(message = "Пользователь обязан зарегистрироваться")
    private String nickname;
}
