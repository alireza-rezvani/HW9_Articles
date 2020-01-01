package ir.maktab32.java.homeworks.hw9.articles.features.dashboard.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.features.dashboard.usecase.GetDashboardByAdminUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.repositories.UserRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;
import ir.maktab32.java.homeworks.hw9.articles.utilities.RoleTitle;

import java.util.List;

public class GetDashboardByAdminUseCaseImpl implements GetDashboardByAdminUseCase {
    @Override
    public String execute() {
        String result;
        if (validation()){
            result = "Number of Users: " + UserRepository.getInstance().findAll().size();
            result += "\nNumber of Articles: " + ArticleRepository.getInstance().findAll().size();
            int publishedCounter = 0;
            int unPublishedCounter = 0;
            List<Article> allArticles = ArticleRepository.getInstance().findAll();
            if (allArticles.size() != 0) {
                for (Article i : allArticles) {
                    if (i.getIsPublished() == true)
                        publishedCounter++;
                    else
                        unPublishedCounter++;
                }
            }
            result += "\nNumber of Published Articles: " + publishedCounter;
            result += "\nNumber of UnPublished Articles: " + unPublishedCounter;
        }
        else {
         result = "Loading Dashboard Failed!";
        }
        return result;
    }

    private boolean validation(){
        boolean result = true;
        if (!CurrentUserStatus.isAdmin()){
            System.out.println("You Are not Signed In as Admin!");
            result = false;
        }
        return result;
    }
}
