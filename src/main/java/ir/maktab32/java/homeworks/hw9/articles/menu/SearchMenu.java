package ir.maktab32.java.homeworks.hw9.articles.menu;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl.FindArticleBasedOnAuthorByUserUseCaseImpl;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl.FindArticleBasedOnIdByUserUseCaseImpl;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl.FindArticleBasedOnTagByUserUseCaseImpl;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl.FindArticleBasedOnTitleByUserUseCaseImpl;

import java.util.List;
import java.util.Scanner;

public class SearchMenu {
    private static Scanner scanner = new Scanner(System.in);
    public static void execute(){
        System.out.println("Search Methods:");
        System.out.println("1. Search Article By id");
        System.out.println("2. Search Article By Title");
        System.out.println("3. Search Article By Author");
        System.out.println("4. Search Article By Tag");

        System.out.print("Your Choice: ");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            Article article = new FindArticleBasedOnIdByUserUseCaseImpl().execute();
            if (article != null)
                System.out.println(article);
        }
        else if (choice.equals("2")) {
            List<Article> articles = new FindArticleBasedOnTitleByUserUseCaseImpl().execute();
            if (articles != null)
                articles.forEach(System.out::println);
        }
        else if (choice.equals("3")) {
            List<Article> articles = new FindArticleBasedOnAuthorByUserUseCaseImpl().execute();
            if (articles != null)
                articles.forEach(System.out::println);
        }
        else if (choice.equals("4")) {
            List<Article> articles = new FindArticleBasedOnTagByUserUseCaseImpl().execute();
            if (articles != null)
                articles.forEach(System.out::println);
        }
        else
            System.out.println("invalid choice");
    }
}
