package com.company.simpljwt.services;

import com.company.simpljwt.model.Article;
import com.company.simpljwt.model.User;
import com.company.simpljwt.repositories.ArticleRepository;
import com.company.simpljwt.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements com.company.simpljwt.services.ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Override
    public Article save(Article article) {
        // get user from db by id
        User user = userRepository.findById(article.getUser().getId()).orElse(null);
        // do relations
        Objects.requireNonNull(user).getArticles().add(article);
        article.setUser(user);

        return articleRepository.save(article);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }
}