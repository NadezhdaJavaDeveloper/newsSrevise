package com.example.newsService.web.model.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertNewsRequest {

    @NotNull(message = "Наименование категории должно быть указано")
    private String categoryName;
    @NotBlank(message = "Содержание новости должно быть указано")
    private String content;
    @NotNull(message = "Id автора новости должен быть указан")
    @Positive(message = "Идентификатор пользователя не может быть отрицательным")
    private Long userId;

}
