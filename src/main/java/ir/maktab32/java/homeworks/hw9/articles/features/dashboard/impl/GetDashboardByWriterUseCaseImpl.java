package ir.maktab32.java.homeworks.hw9.articles.features.dashboard.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.FindArticleBasedOnAuthorByUserUseCase;
import ir.maktab32.java.homeworks.hw9.articles.features.dashboard.usecase.GetDashboardByWriterUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.RoleTitle;

import java.util.List;

public class GetDashboardByWriterUseCaseImpl implements GetDashboardByWriterUseCase {
    @Override
    public String execute() {
        String result;
        if (getWriterDashboardValidation()){
            List<Article> allArticles = ArticleRepository.getInstance().findAll();
            result = "Number of All Articles: " + allArticles.size();
            int currentUserArticlesCounter = 0;
            for (Article i : allArticles)
                if (i.getAuthor().equals(AuthenticationService.getInstance().getSignedInUser()))
                    currentUserArticlesCounter++;
            result += "\nNumber of Your Articles: " + currentUserArticlesCounter;
        }
        else {
            result = "Loading Dashboard Failed!";
        }
        return result;
    }

    private boolean getWriterDashboardValidation(){
        boolean result = true;
        List<Role> currentUserRoles = AuthenticationService.getInstance().getSignedInUser().getRoles();
        boolean isWriter = false;
        for (Role i : currentUserRoles)
            if (i.getTitle().equals(RoleTitle.WRITER)) {
                isWriter = true;
                break;
            }
        if (!isWriter){
            System.out.println("You Are not Signed In as Writer!");
            result = false;
        }
        return result;
    }
}
