package ir.maktab32.java.homeworks.hw9.articles;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl.*;
import ir.maktab32.java.homeworks.hw9.articles.features.categorymanagement.impl.AddCategoryByAdminUseCaseImpl;
import ir.maktab32.java.homeworks.hw9.articles.features.tagmanagement.impl.AddTagByAdminUseCaseImpl;
import ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.impl.*;
import ir.maktab32.java.homeworks.hw9.articles.menu.CommandsMenu;
import ir.maktab32.java.homeworks.hw9.articles.menu.DashboardMenu;
import ir.maktab32.java.homeworks.hw9.articles.menu.SearchMenu;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.utilities.Defaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticlesApplication {
    public static void main(String[] args) {

        Defaults.execute();

        Scanner scanner = new Scanner(System.in);

        String command = "";

        while (!command.equals("exit")){
            System.out.println("\u29bf Input Command:\t(menu --> Menu)");
            command = scanner.nextLine();

            if (command.equals("menu")){
                CommandsMenu.execute();
            }
            else if (command.equals("sign up")){
                new SignUpUseCaseImpl().execute();
            }
            else if (command.equals("sign in")){
                new SignInUseCaseImpl().execute();
            }
            else if (command.equals("sign out")){
                new SignOutUseCaseImpl().execute();
            }
            else if (command.equals("change pass")){
                new ChangePasswordByUserUseCaseImpl().execute();
            }
            else if (command.equals("change role")){
                new ChangeUserRoleByAdminUseCaseImpl().execute();
            }
            else if (command.equals("add tag")){
                new AddTagByAdminUseCaseImpl().execute();
            }
            else if (command.equals("dashboard")){
                DashboardMenu.execute();
            }
            else if (command.equals("add category")){
                new AddCategoryByAdminUseCaseImpl().execute();
            }
            else if (command.equals("add article")){
                new AddArticleByWriterUseCaseImpl().execute();
            }
            else if (command.equals("delete article")){
                new DeleteArticleByAdminUseCaseImpl().execute();
            }
            else if (command.equals("edit article")){
                new EditArticleByWriterUseCaseImpl().execute();
            }
            else if (command.equals("all articles")){
                List<Article> articles = new FindAllArticlesByUserUseCaseImpl().execute();
                if (articles != null) {
                    for (Article i : articles) {
                        System.out.println("\tId: " + i.getId() + "\tTitle: "
                                + i.getTitle() + "\tBrief: " + i.getBrief());
                    }
                    System.out.println("\t\t\u2705 Go to 'search' to See Article Details!");
                }
            }
            else if (command.equals("search")){
                SearchMenu.execute();
            }
            else if (command.equals("my articles")){
                List<Article> articles = new FindWriterArticlesByWriterUseCaseImpl().execute();
                if (articles != null)
                    articles.forEach(System.out::println);

            }
            else if (command.equals("publish")){
                new PublishArticleByAdminUseCaseImpl().execute();
            }
            else if (command.equals("unpublish")){
                new UnPublishArticleByAdminUseCaseImpl().execute();
            }
            else if (command.equals("exit")){
                System.out.println("\t\u2705 Bye!");
            }
            else {
                System.out.println("\t\u26a0 Invalid Command!");
            }
        }
    }
}
