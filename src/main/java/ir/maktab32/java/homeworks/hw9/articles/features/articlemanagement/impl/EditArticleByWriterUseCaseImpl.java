package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.EditArticleByWriterUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;
import ir.maktab32.java.homeworks.hw9.articles.utilities.IsNumeric;

import java.util.Date;
import java.util.Scanner;


public class EditArticleByWriterUseCaseImpl implements EditArticleByWriterUseCase {
    @Override
    public Article execute() {
        Article result;
        Article validatedArticle = inputAndValidation();
        if (validatedArticle != null){
            ArticleRepository.getInstance().update(validatedArticle);
            System.out.println("\t\t\t\u2705 Article Edited Successfully!");
            result = validatedArticle;
        }
        else {
            System.out.println("\t\t\t\u26a0 Editing Article Failed!");
            result = null;
        }
        return result;
    }

    private Article inputAndValidation(){
        Scanner scanner = new Scanner(System.in);
        Article result;
        if (!CurrentUserStatus.isWriter()){
            System.out.println("\t\u26a0 Sign In As Writer!");
            result = null;
        }
        else {
            System.out.print("\t\u29bf Article Id: ");
            String articleId = scanner.nextLine();
            if (IsNumeric.execute(articleId) && ArticleRepository.getInstance().isExisting(Long.parseLong(articleId))){
                Article articleFromDatabase = ArticleRepository.getInstance().findById(Long.parseLong(articleId));

                if (articleFromDatabase.getAuthor().equals(AuthenticationService.getInstance().getSignedInUser())) {
                    System.out.println("\t\t\u29bf Enter New Title:\t(Press * to Skip)");
                    String newTitle = scanner.nextLine();
                    if (newTitle.equals("*") || newTitle.isEmpty())
                        newTitle = articleFromDatabase.getTitle();

                    System.out.println("\t\t\u29bf Enter New Brief:\t(Press * to Skip)");
                    String newBrief = scanner.nextLine();
                    if (newBrief.equals("*"))
                        newBrief = articleFromDatabase.getBrief();

                    System.out.println("\t\t\u29bf Enter New Content:\t(Press * to Skip)");
                    String newContent = scanner.nextLine();
                    if (newContent.equals("*"))
                        newContent = articleFromDatabase.getContent();

                    //todo: other attributes can be edited too

                    if (newTitle.equals(articleFromDatabase.getTitle()) && newBrief.equals(articleFromDatabase.getBrief())
                            && newContent.equals(articleFromDatabase.getContent())) {
                        System.out.println("\t\t\t\u26a0 No Changes Done!");
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
                    System.out.println("\t\t\u26a0 You Aren't Allowed to Edit This Article!");
                    result = null;
                }
            }
            else {
                System.out.println("\t\t\u26a0 Invalid Article Id!");
                result = null;
            }
        }
        return result;
    }
}
