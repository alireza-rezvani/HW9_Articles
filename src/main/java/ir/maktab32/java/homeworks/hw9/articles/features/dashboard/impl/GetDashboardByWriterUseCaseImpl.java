package ir.maktab32.java.homeworks.hw9.articles.features.dashboard.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.features.dashboard.usecase.GetDashboardByWriterUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;

import java.util.List;

public class GetDashboardByWriterUseCaseImpl implements GetDashboardByWriterUseCase {
    @Override
    public String execute() {
        String result;
        if (validation()){
            List<Article> allArticles = ArticleRepository.getInstance().findAll();
            result = "\t\u2705 Number of All Articles: " + allArticles.size();
            int currentUserArticlesCounter = 0;
            for (Article i : allArticles)
                if (i.getAuthor().equals(AuthenticationService.getInstance().getSignedInUser()))
                    currentUserArticlesCounter++;
            result += "\n\t\u2705 Number of Your Articles: " + currentUserArticlesCounter;
        }
        else {
            result = "\t\u26a0 Loading Dashboard Failed!";
        }
        return result;
    }

    private boolean validation(){
        boolean result = true;

        if (!CurrentUserStatus.isWriter()){
            System.out.println("\t\u26a0 You Aren't Signed In as Writer!");
            result = false;
        }
        return result;
    }
}
