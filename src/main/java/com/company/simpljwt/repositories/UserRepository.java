package com.company.simpljwt.repositories;

import com.company.simpljwt.model.Color;
import com.company.simpljwt.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserRepository extends CrudRepository<User, Long> {

    Collection<User> findAllByAgeGreaterThanEquals(int age);

    User findByLogin(String login);

    @Query(value = "SELECT u.* FROM article a JOIN user u ON u.id = a.user_id  WHERE a.color = :#{#color.name()}", nativeQuery = true)
    Collection<User> findAllByArticleColor(@Param("color") Color color);

    @Query(value = "SELECT user.* FROM user JOIN article ON user.id = article.user_id GROUP BY article.user_id HAVING COUNT(article.user_id) >= :counter", nativeQuery = true)
    Collection<User> findAllByArticlesCount(@Param("counter") int counter);
}