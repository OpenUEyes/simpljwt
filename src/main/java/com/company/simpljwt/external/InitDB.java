package com.company.simpljwt.external;

import com.company.simpljwt.model.Article;
import com.company.simpljwt.model.Color;
import com.company.simpljwt.model.Role;
import com.company.simpljwt.model.User;
import com.company.simpljwt.services.ArticleService;
import com.company.simpljwt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitDB implements CommandLineRunner {

    private final UserService userService;
    private final ArticleService articleService;

    @Override
    public void run(String... args) {
        loadData();
    }

    private void loadData() {
        User user1 = User.builder().login("testlogin").password("testpassword").role(Role.USER).name("user1").age(24).build();
        User user2 = User.builder().login("testloginnumbertwo").password("testpasswordnumbertwo").role(Role.USER).name("user2").age(35).build();
        User user3 = User.builder().login("testloginnumberthree").password("testpasswordnumberthree").role(Role.USER).name("user3").age(21).build();

        User savedUser1 = userService.save(user1);
        User savedUser2 = userService.save(user2);
        User savedUser3 = userService.save(user3);

        Article article1 = Article.builder().color(Color.BLACK).text("article1 text").user(savedUser1).build();
        Article article2 = Article.builder().color(Color.WHITE).text("article2 text").user(savedUser2).build();
        Article article3 = Article.builder().color(Color.RED).text("article3 text").user(savedUser2).build();
        Article article4 = Article.builder().color(Color.GREEN).text("article4 text").user(savedUser3).build();
        Article article5 = Article.builder().color(Color.RED).text("article5 text").user(savedUser3).build();
        Article article6 = Article.builder().color(Color.BLUE).text("article6 text").user(savedUser3).build();

        articleService.save(article1);
        articleService.save(article2);
        articleService.save(article3);
        articleService.save(article4);
        articleService.save(article5);
        articleService.save(article6);
    }
}