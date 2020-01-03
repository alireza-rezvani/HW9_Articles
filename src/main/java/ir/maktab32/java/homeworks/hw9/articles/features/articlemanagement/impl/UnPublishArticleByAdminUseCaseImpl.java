package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.UnPublishArticleByAdminUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;
import ir.maktab32.java.homeworks.hw9.articles.utilities.IsNumeric;

import java.util.Scanner;

public class UnPublishArticleByAdminUseCaseImpl implements UnPublishArticleByAdminUseCase {
    @Override
    public boolean execute() {
        boolean result;
        Long validatedId = inputAndValidation();
        if (validatedId != null){
            Article article = ArticleRepository.getInstance().findById(validatedId);
            article.setIsPublished(false);
            article.setPublishDate(null);
            ArticleRepository.getInstance().update(article);
            System.out.println("\t\t\u2705 Article UnPublished Successfully!");
            result = true;
        }
        else {
            System.out.println("\t\t\u26a0 Article UnPublish Failed!");
            result = false;
        }
        return result;
    }

    private Long inputAndValidation(){
        Scanner scanner = new Scanner(System.in);
        Long result;

        if (!CurrentUserStatus.isAdmin()){
            System.out.println("\t\u26a0 Sign In As Admin!");
            result = null;
        }
        else {
            System.out.print("\t\u29bf Article Id: ");
            String articleId = scanner.nextLine();
            if (IsNumeric.execute(articleId) && ArticleRepository.getInstance().isExisting(Long.parseLong(articleId))){
                if (ArticleRepository.getInstance().findById(Long.parseLong(articleId)).getIsPublished() == false){
                    System.out.println("\t\t\u26a0 This Article Is UnPublished Already!");
                    result = null;
                }
                else {
                    result = Long.parseLong(articleId);
                }
            }
            else {
                System.out.println("\t\t\u26a0 Invalid Article Id!");
                result = null;
            }
        }

        return result;
    }
}
