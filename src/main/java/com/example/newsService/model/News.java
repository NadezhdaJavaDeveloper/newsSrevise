package com.example.newsService.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "news")
public class News implements Editable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id",
            nullable = true)
    private Category category;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

    public void addComment(Comment comment) {
        if(!commentList.contains(comment)) {
            commentList.add(comment);
            comment.setNews(this);
        }
    }

    public void removeComment(Comment comment) {
        commentList.remove(comment);
        comment.detachFromNews();
    }

}
