package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;

import java.util.List;

public interface FindArticleBasedOnTitleByUserUseCase {
    List<Article> execute();
}
