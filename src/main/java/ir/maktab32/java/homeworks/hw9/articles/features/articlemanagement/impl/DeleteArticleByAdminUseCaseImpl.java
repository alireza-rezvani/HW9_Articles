package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.DeleteArticleByAdminUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;
import ir.maktab32.java.homeworks.hw9.articles.utilities.IsNumeric;
import ir.maktab32.java.homeworks.hw9.articles.utilities.RoleTitle;

import java.util.Scanner;

public class DeleteArticleByAdminUseCaseImpl implements DeleteArticleByAdminUseCase {
    @Override
    public boolean execute() {
        boolean result;
        Long validatedArticleId = inputAndValidate();
        if (validatedArticleId != null){
            ArticleRepository.getInstance().removeById(validatedArticleId);
            result = true;
            System.out.println("article deleted successfully");
        }
        else {
            result = false;
            System.out.println("deleting article failed");
        }
        return result;
    }

private Long inputAndValidate(){
    Scanner scanner = new Scanner(System.in);
    Long result;
    if (!CurrentUserStatus.isAdmin()){
        System.out.println("sign in as admin");
        result = null;
    }
    else {
        System.out.print("article id: ");
        String articleId = scanner.nextLine();
        if (IsNumeric.execute(articleId) && ArticleRepository.getInstance().isExisting(Long.parseLong(articleId))){
            result = Long.parseLong(articleId);
        }
        else {
            System.out.println("Invalid article id");
            result = null;
        }
    }
    return result;
    }
}
