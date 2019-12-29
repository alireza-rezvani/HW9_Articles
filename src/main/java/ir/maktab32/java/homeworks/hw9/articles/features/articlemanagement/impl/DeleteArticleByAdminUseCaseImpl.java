package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.DeleteArticleByAdminUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.RoleTitle;

public class DeleteArticleByAdminUseCaseImpl implements DeleteArticleByAdminUseCase {
    @Override
    public boolean execute(Long id) {
        boolean result;
        if (deleteArticleValidation(id)){
            ArticleRepository.getInstance().removeById(id);
            result = true;
            System.out.println("Article Deleted Successfully!");
        }
        else {
            System.out.println("Delete Failed!");
            result = false;
        }
        return result;
    }

    private boolean deleteArticleValidation(Long id){
        boolean result = true;
        User currentUser = AuthenticationService.getInstance().getSignedInUser();
        if (currentUser == null){
            System.out.println("Please Sign In as Admin!");
            result = false;
        }
        else{
            boolean isAdmin = false;
            for (Role i : currentUser.getRoles())
                if (i.getTitle().equals(RoleTitle.ADMIN)) {
                    isAdmin = true;
                    break;
                }
            if (!isAdmin){
                System.out.println("Please Sign In as Admin!");
                result = false;
            }
            else {
                Article requestedArticle = ArticleRepository.getInstance().findById(id);
                if (requestedArticle == null){
                    System.out.println("Requested Article Doesn't Exist in Database!");
                    result = false;
                }
            }
        }
        return result;
    }
}
