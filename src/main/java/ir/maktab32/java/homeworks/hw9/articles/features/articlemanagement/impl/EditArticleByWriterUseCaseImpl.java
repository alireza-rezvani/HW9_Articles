package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.EditArticleByWriterUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;
import ir.maktab32.java.homeworks.hw9.articles.utilities.IsNumeric;
import ir.maktab32.java.homeworks.hw9.articles.utilities.RoleTitle;

import java.util.Date;
import java.util.Scanner;


public class EditArticleByWriterUseCaseImpl implements EditArticleByWriterUseCase {
    @Override
    public Article execute() {
        Article result;
        Article validatedArticle = inputAndValidation();
        if (validatedArticle != null){
            ArticleRepository.getInstance().update(validatedArticle);
            System.out.println("article edited successfully");
            result = validatedArticle;
        }
        else {
            System.out.println("editing article failed");
            result = null;
        }
        return result;
    }

    private Article inputAndValidation(){
        Scanner scanner = new Scanner(System.in);
        Article result;
        if (!CurrentUserStatus.isWriter()){
            System.out.println("sign in as writer");
            result = null;
        }
        else {
            System.out.print("Article id: ");
            String articleId = scanner.nextLine();
            if (IsNumeric.execute(articleId) && ArticleRepository.getInstance().isExisting(Long.parseLong(articleId))){
                Article articleFromDatabase = ArticleRepository.getInstance().findById(Long.parseLong(articleId));

                if (articleFromDatabase.getAuthor().equals(AuthenticationService.getInstance().getSignedInUser())) {
                    System.out.println("enter new title (press * to skip)");
                    String newTitle = scanner.nextLine();
                    if (newTitle.equals("*") || newTitle.isEmpty())
                        newTitle = articleFromDatabase.getTitle();

                    System.out.println("enter new brief (press * to skip)");
                    String newBrief = scanner.nextLine();
                    if (newBrief.equals("*"))
                        newBrief = articleFromDatabase.getBrief();

                    System.out.println("enter new content (press * to skip)");
                    String newContent = scanner.nextLine();
                    if (newContent.equals("*"))
                        newContent = articleFromDatabase.getContent();

                    //todo: other attributes can be edited too

                    if (newTitle.equals(articleFromDatabase.getTitle()) && newBrief.equals(articleFromDatabase.getBrief())
                            && newContent.equals(articleFromDatabase.getContent())) {
                        System.out.println("no changes made");
                        result = null;
                    } else {
                        articleFromDatabase.setTitle(newTitle);
                        articleFromDatabase.setBrief(newBrief);
                        articleFromDatabase.setContent(newContent);
                        articleFromDatabase.setLastUpdateDate(new Date());

                        result = articleFromDatabase;
                    }
                }
                else {
                    System.out.println("you are not allowed to edit this article");
                    result = null;
                }
            }
            else {
                System.out.println("invalid article id");
                result = null;
            }
        }
        return result;
    }
}
