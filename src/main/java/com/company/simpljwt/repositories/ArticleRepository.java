package com.company.simpljwt.repositories;

import com.company.simpljwt.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}