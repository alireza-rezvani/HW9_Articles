package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.DeleteArticleByAdminUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;
import ir.maktab32.java.homeworks.hw9.articles.utilities.IsNumeric;

import java.util.Scanner;

public class DeleteArticleByAdminUseCaseImpl implements DeleteArticleByAdminUseCase {
    @Override
    public boolean execute() {
        boolean result;
        Long validatedArticleId = inputAndValidate();
        if (validatedArticleId != null){
            ArticleRepository.getInstance().removeById(validatedArticleId);
            result = true;
            System.out.println("\t\t\u2705 Article Deleted Successfully!");
        }
        else {
            result = false;
            System.out.println("\t\t\u26a0 Deleting Article Failed!");
        }
        return result;
    }

private Long inputAndValidate(){
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
            result = Long.parseLong(articleId);
        }
        else {
            System.out.println("\t\t\u26a0 Invalid Article Id!");
            result = null;
        }
    }
    return result;
    }
}
