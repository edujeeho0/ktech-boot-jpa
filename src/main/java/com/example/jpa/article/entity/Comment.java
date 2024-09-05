package com.example.jpa.article.entity;

import com.example.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {
    private String content;
    private String writer;

    @ManyToOne
    private Article article;
}
