package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.FindArticleBasedOnTitleByUserUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

public class FindArticleBasedOnTitleByUserUseCaseImpl implements FindArticleBasedOnTitleByUserUseCase {
    @Override
    public List<Article> list(String title) {
        List<Article> result;
        if (validation(title)){
            List<Article> allArticles = ArticleRepository.getInstance().findAll();
            result = new ArrayList<>();
            for (Article i : allArticles) {
                if (i.getTitle().contains(title))
                    result.add(i);
            }
            if (result.size() != 0)
                System.out.println("Articles Found Successfully!");
            else
                System.out.println("No Matches Found!");
        }
        else {
            System.out.println("Loading Articles Failed!");
            result = null;
        }
        return result;
    }

    private boolean validation(String title){
        boolean result = true;
        //no validation to check
        return result;
    }
}
