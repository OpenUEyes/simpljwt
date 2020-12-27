package com.company.simpljwt.external;

import com.company.simpljwt.model.Article;
import com.company.simpljwt.model.Color;
import com.company.simpljwt.model.Role;
import com.company.simpljwt.model.User;
import com.company.simpljwt.repositories.ArticleRepository;
import com.company.simpljwt.repositories.RoleRepository;
import com.company.simpljwt.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class InitDB implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final RoleRepository roleRepository;


    public InitDB(UserRepository userRepository, ArticleRepository articleRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        if (((Collection) userRepository.findAll()).size() == 0) {
            loadData();
        }
    }

    private void loadData() {
        Role role_admin = Role.builder().name("ROLE_ADMIN").build();
        Role role_user = Role.builder().name("ROLE_USER").build();
        roleRepository.save(role_admin);
        roleRepository.save(role_user);



        User user1 = User.builder().name("user1").age(24).build();
        User user2 = User.builder().name("user2").age(35).build();
        User user3 = User.builder().name("user3").age(21).build();

        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);
        User savedUser3 = userRepository.save(user3);

        Article article1 = Article.builder().color(Color.BLACK).text("article1 text").user(savedUser1).build();
        Article article2 = Article.builder().color(Color.WHITE).text("article2 text").user(savedUser2).build();
        Article article3 = Article.builder().color(Color.RED).text("article3 text").user(savedUser2).build();
        Article article4 = Article.builder().color(Color.GREEN).text("article4 text").user(savedUser3).build();
        Article article5 = Article.builder().color(Color.RED).text("article5 text").user(savedUser3).build();
        Article article6 = Article.builder().color(Color.BLUE).text("article6 text").user(savedUser3).build();

        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);
        articleRepository.save(article4);
        articleRepository.save(article5);
        articleRepository.save(article6);

//        System.out.println(userRepository.findAllByAgeGreaterThanEqual(24));
//        System.out.println(userService.findAllByArticleColor(Color.RED));
//        System.out.println(userService.findAllNamesByArticlesCount(2));
    }
}