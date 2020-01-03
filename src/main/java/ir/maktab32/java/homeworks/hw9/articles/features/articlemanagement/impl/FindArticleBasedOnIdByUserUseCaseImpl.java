package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.FindArticleBasedOnIdByUserUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.utilities.IsNumeric;

import java.util.Scanner;

public class FindArticleBasedOnIdByUserUseCaseImpl implements FindArticleBasedOnIdByUserUseCase {
    @Override
    public Article execute() {
        Article result;
        Long validatedId = inputAndValidation();
        if (validatedId != null){
            result = ArticleRepository.getInstance().findById(validatedId);
            System.out.println("\t\t\u2705 Article Loaded Successfully!");
        }
        else {
            System.out.println("\t\t\u26a0 Loading Article Failed!");
            result = null;
        }
        return result;
    }

    private Long inputAndValidation(){
        Scanner scanner = new Scanner(System.in);
        Long result;

        System.out.print("\t\u29bf Article Id: ");
        String articleId = scanner.nextLine();
        if (IsNumeric.execute(articleId) && ArticleRepository.getInstance().isExisting(Long.parseLong(articleId))){
            result = Long.parseLong(articleId);
        }
        else {
            System.out.println("\t\t\u26a0 Invalid Article Id!");
            result = null;
        }

        return result;

    }
}
