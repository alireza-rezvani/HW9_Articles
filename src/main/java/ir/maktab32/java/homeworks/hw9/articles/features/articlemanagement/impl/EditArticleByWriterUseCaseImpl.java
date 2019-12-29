package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.EditArticleByWriterUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.RoleTitle;


public class EditArticleByWriterUseCaseImpl implements EditArticleByWriterUseCase {
    @Override
    public Article execute(Article article) {
        Article result;
        if (editArticleValidation(article)){
            result = ArticleRepository.getInstance().update(article);
            System.out.println("Article Edited Successfully!");
        }
        else {
            System.out.println("Edit Article Failed!");
            result = null;
        }
        return result;
    }

    private boolean editArticleValidation(Article article){
        boolean result = true;
        User currentUser = AuthenticationService.getInstance().getSignedInUser();
        if (currentUser == null){
            System.out.println("Please Sign in as Article Writer!");
            result = false;
        }
        else {
            boolean isWriter = false;
            for (Role i : currentUser.getRoles())
                if (i.getTitle().equals(RoleTitle.WRITER)) {
                    isWriter = true;
                    break;
                }
            if (!isWriter){
                System.out.println("Please Sign In as Writer!");
                result = false;
            }
            else {
                Long requestArticleId = article.getId();
                Article findArticleByIdFromDatabase = ArticleRepository.getInstance().findById(requestArticleId);
                if (findArticleByIdFromDatabase == null){
                    System.out.println("This Article Doesn't Exist in Article!");
                    result = false;
                }
                else if (!findArticleByIdFromDatabase.getAuthor().equals(currentUser)){
                    System.out.println("You Are not Allowed to Edit This Article!");
                    result = false;
                }
            }
        }
        return result;
    }
}
