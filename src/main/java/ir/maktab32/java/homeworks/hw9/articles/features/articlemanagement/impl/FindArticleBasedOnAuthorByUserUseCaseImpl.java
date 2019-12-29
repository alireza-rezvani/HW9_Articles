package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.FindArticleBasedOnAuthorByUserUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.repositories.UserRepository;
import ir.maktab32.java.homeworks.hw9.articles.utilities.RoleTitle;

import java.util.ArrayList;
import java.util.List;

public class FindArticleBasedOnAuthorByUserUseCaseImpl implements FindArticleBasedOnAuthorByUserUseCase {
    @Override
    public List<Article> list(String authorUsername) {
        List<Article> result;
        if (validation(authorUsername)){
            List<Article> allArticles = ArticleRepository.getInstance().findAll();
            result = new ArrayList<>();
            for (Article i : allArticles) {
                if (i.getAuthor().getUsername().equals(authorUsername))
                    result.add(i);
            }
            if (result.size() != 0)
                System.out.println("Articles Found Successfully!");
            else
                System.out.println("This Writer has No Articles!");
        }
        else {
            System.out.println("Loading Articles Failed!");
            result = null;
        }
        return result;
    }

    private boolean validation(String authorUsername){
        boolean result = true;
        List<User> allUsers = UserRepository.getInstance().findAll();
        boolean userExists = false;
        for (User i : allUsers){
            if (i.getUsername().equals(authorUsername)){
                boolean isWriter = false;
                for (Role j : i.getRoles()){
                    if (j.getTitle().equals(RoleTitle.WRITER)){
                        isWriter = true;
                        break;
                    }
                }
                if (!isWriter){
                    System.out.println("Requested User is not A Writer!");
                    result = false;
                }

                userExists = true;
                break;
            }
        }
        if (!userExists){
            System.out.println("This Username Doesn't Exist in Database!");
            result = false;
        }
        return result;
    }
}
