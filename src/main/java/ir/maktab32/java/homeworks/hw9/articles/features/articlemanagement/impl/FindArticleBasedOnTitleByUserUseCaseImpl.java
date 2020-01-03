package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.FindArticleBasedOnTitleByUserUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindArticleBasedOnTitleByUserUseCaseImpl implements FindArticleBasedOnTitleByUserUseCase {
    @Override
    public List<Article> execute() {
        List<Article> result;
        String validatedTitle = inputAndValidation();
        if (validatedTitle != null){
            List<Article> allArticles = ArticleRepository.getInstance().findAll();
            result = new ArrayList<>();
            for (Article i : allArticles) {
                if (i.getTitle().contains(validatedTitle))
                    result.add(i);
            }
            if (result.size() != 0)
                System.out.println("\t\t\u2705 Articles Found Successfully!");
            else {
                System.out.println("\t\t\u26a0 No Matches Found!");
                result = null;
            }
        }
        else {
            System.out.println("\t\t\u26a0 Loading Articles Failed!");
            result = null;
        }
        return result;
    }

    private String inputAndValidation(){
        Scanner scanner = new Scanner(System.in);
        String result;

        System.out.print("\t\u29bf Title: ");
        result = scanner.nextLine();

        //nothing to check
        return result;
    }
}
