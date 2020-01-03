package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.FindAllArticlesByUserUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;

import java.util.List;

public class FindAllArticlesByUserUseCaseImpl implements FindAllArticlesByUserUseCase {
    @Override
    public List<Article> execute() {
        List<Article> result;
        if (validation()) {
            result = ArticleRepository.getInstance().findAll();
            System.out.println("\t\u2705 Articles Loaded Successfully!");
        }
        else {
            System.out.println("\t\u26a0 Articles Loading Failed!");
            result = null;
        }
        return result;
    }

    private boolean validation(){
        boolean result = true;
        if (ArticleRepository.getInstance().findAll().size() == 0){
            System.out.println("\t\u26a0 There is No Article In Database!");
            result = false;
        }
        return result;
    }

}
