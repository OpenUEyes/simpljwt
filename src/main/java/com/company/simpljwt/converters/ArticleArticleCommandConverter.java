package com.company.simpljwt.converters;

import com.company.simpljwt.commands.ArticleCommand;
import com.company.simpljwt.model.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleArticleCommandConverter {

    Article commandToModel(ArticleCommand command);

    ArticleCommand modelToCommand(Article article);
}