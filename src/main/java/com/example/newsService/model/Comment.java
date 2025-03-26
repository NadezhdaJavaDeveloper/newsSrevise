package com.example.newsService.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "comments")
public class Comment implements Editable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    public void setNews(News news) {
        if(this.news != null && !this.news.getId().equals(news.getId())) {
            throw new IllegalStateException("Комментарий не может изменить привязку к новости");
        }

        this.news = news;
    }

    void detachFromNews() {
        this.news = null;
    }

//    public void setNews(News news) {
//        if(this.news == null) {
//            this.news = news;
//            news.getCommentList().add(this);
//        } else {
//            throw new IllegalStatetException("Комментарий не может изменить привязку к новости");
//        }
//    }
}
