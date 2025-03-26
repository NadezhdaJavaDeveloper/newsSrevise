package com.example.newsService.web.model.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertCommentRequest {

    @NotBlank(message = "Вы забыли указать комментарий!")
    private String content;
    @NotNull(message = "Укажите идентификатор пользователя")
    @Positive(message = "Идентификатор пользователя не может быть отрицательным")
    private Long userId;
    @NotNull(message = "Укажите идентификатор новости")
    @Positive(message = "Идентификатор новости не может быть отрицательным")
    private Long newsId;


}
