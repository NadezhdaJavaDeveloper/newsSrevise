package com.example.newsService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<News> newsList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

//    public void createNews (News news) {
//        if(newsList == null) {
//            newsList = new ArrayList<>();
//        }
//        newsList.add(news);
//    }
//
//    public void removeNews(Long newsId) {
//        newsList = newsList.stream()
//                .filter(news -> !news.getId().equals(newsId))
//                .collect(Collectors.toList());
//    }
//
//    public void createComment (Comment comment) {
//        if(commentList == null) {
//            commentList = new ArrayList<>();
//        }
//        commentList.add(comment);
//    }
//
//    public void removeComment (Long commentId) {
//        commentList = commentList.stream()
//                .filter(comment -> !comment.getId().equals(commentId))
//                .collect(Collectors.toList());
//    }
}
