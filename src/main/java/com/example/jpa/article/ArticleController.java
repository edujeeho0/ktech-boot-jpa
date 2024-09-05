package com.example.jpa.article;

import com.example.jpa.article.entity.Article;
import com.example.jpa.article.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleRepository repository;

    @GetMapping("test")
    public String test() {
        repository.save(Article.builder()
                .title("test")
                .content("test")
                .writer("test")
                .build());
        return "done";
    }

    @GetMapping("test-cascade")
    public String testCascade() {
        // 1. 하나의 게글을 만들고,
        Article article = Article.builder()
                .title("test")
                .content("test")
                .writer("test")
                .build();

        // 2. 두개의 댓글을 만든 다음
        Comment comment1 = Comment.builder()
                .content("test comment 1")
                .writer("test1")
                .article(article)
                .build();
        Comment comment2 = Comment.builder()
                .content("test comment 2")
                .writer("test2")
                .article(article)
                .build();

        // 3. 각각 게시글에 추가를 해준다.
        article.getComments().add(comment1);
        article.getComments().add(comment2);

        // 4. 게시글을 저장한다.
        repository.save(article);

        return "done";
    }
}
