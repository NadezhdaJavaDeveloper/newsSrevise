package com.example.newsService.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    @OneToMany(mappedBy = "category",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = false)
    @ToString.Exclude
    @Builder.Default
    private List<News> newsList = new ArrayList<>();

    @PreRemove
    private void preRemove() {
        for(News news : newsList) {
            news.setCategory(null);
        }
        newsList.clear();
    }
}
