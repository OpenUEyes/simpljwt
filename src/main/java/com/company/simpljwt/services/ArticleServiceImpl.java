package com.company.simpljwt.services;

import com.company.simpljwt.commands.ArticleCommand;
import com.company.simpljwt.converters.ArticleArticleCommandConverter;
import com.company.simpljwt.model.Article;
import com.company.simpljwt.model.User;
import com.company.simpljwt.repositories.ArticleRepository;
import com.company.simpljwt.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleArticleCommandConverter mapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository, ArticleArticleCommandConverter mapper) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public ArticleCommand save(ArticleCommand command) {
        Article article = mapper.commandToModel(command);
        // get user from db by id
        User user = userRepository.findById(article.getUser().getId()).orElse(null);
        // do relations
        Objects.requireNonNull(user).getArticles().add(article);
        article.setUser(user);

        return mapper.modelToCommand(articleRepository.save(article));
    }
}