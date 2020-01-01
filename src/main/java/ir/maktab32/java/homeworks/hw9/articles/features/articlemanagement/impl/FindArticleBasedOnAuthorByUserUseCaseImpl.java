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
import java.util.Scanner;

public class FindArticleBasedOnAuthorByUserUseCaseImpl implements FindArticleBasedOnAuthorByUserUseCase {
    @Override
    public List<Article> execute() {
        List<Article> result;
        User validatedUser = inputAndValidation();
        if (validatedUser != null){
            List<Article> allArticles = ArticleRepository.getInstance().findAll();
            result = new ArrayList<>();
            for (Article i : allArticles) {
                if (i.getAuthor().equals(validatedUser))
                    result.add(i);
            }
            if (result.size() != 0)
                System.out.println("Articles Found Successfully!");
            else {
                System.out.println("This Writer has No Articles!");
                result = null;
            }
        }
        else {
            System.out.println("Loading Articles Failed!");
            result = null;
        }
        return result;
    }

    private User inputAndValidation(){
        Scanner scanner = new Scanner(System.in);
        User result;
        System.out.println("Writer's Username: ");
        String authorUsername = scanner.nextLine();
        List<User> allUsers = UserRepository.getInstance().findAll();
        User requestedUser = null;
        for (User i : allUsers){
            if (i.getUsername().equals(authorUsername)){
                requestedUser = i;
                break;
            }
        }
        if (requestedUser == null){
            System.out.println("This Username Doesn't Exist in Database!");
            result = null;
        }
        else {
            boolean isWriter = false;
            for (Role i : requestedUser.getRoles()){
                if (i.getTitle().equals(RoleTitle.WRITER)){
                    isWriter = true;
                    break;
                }
            }
            if (!isWriter){
                System.out.println("Requested User is not a writer");
                result = null;
            }
            else {
                result = requestedUser;
            }
        }
        return result;
    }
}
