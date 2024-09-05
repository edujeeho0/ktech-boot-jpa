package com.example.jpa.article;

import com.example.jpa.article.entity.Article;
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
}
