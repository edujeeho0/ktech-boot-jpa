package com.example.jpa.article.entity;

import com.example.jpa.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article extends BaseEntity {
    private String title;
    private String content;
    private String writer;

    @OneToMany(
            mappedBy = "article",
//            cascade = CascadeType.ALL
            // PERSIST: article이 저장될 때 연관된 comment를 같이 저장
//            cascade = CascadeType.PERSIST
            // REMOVE: article이 삭제될 때, 연관된 comment를 같이 삭제
            cascade = CascadeType.REMOVE
    )
    private final List<Comment> comments = new ArrayList<>();
}
