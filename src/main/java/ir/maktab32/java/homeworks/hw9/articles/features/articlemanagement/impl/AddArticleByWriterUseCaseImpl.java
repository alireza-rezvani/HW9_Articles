package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.*;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.AddArticleByWriterUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.repositories.CategoryRepository;
import ir.maktab32.java.homeworks.hw9.articles.repositories.TagRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;
import ir.maktab32.java.homeworks.hw9.articles.utilities.IsNumeric;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AddArticleByWriterUseCaseImpl implements AddArticleByWriterUseCase {
    @Override
    public Article execute() {
        Article result;
        Article validatedArticle = inputAndValidation();
        if (validatedArticle != null){
            result = ArticleRepository.getInstance().save(validatedArticle);
            System.out.println("\t\t\u2705 Article Added Successfully!");
        }
        else {
            System.out.println("\t\t\u26a0 Article Add Failed!");
            result = null;
        }
        return result;
    }

    private Article inputAndValidation(){
        Article result = null;

        Scanner scanner = new Scanner(System.in);

        if (!CurrentUserStatus.isSignedIn())
            System.out.println("\t\u26a0 Sign In First!");
        else if (!CurrentUserStatus.isWriter())
            System.out.println("\t\u26a0 Sign In As Writer!");
        else {
            String title = inputTitle();
            System.out.print("\t\u29bf Brief: ");
            String brief = scanner.nextLine();
            System.out.print("\t\u29bf Content: ");
            String content = scanner.nextLine();
            List<Tag> tags = inputTags();
            Category category = inputCategory();
            if (category != null){
                result = new Article(null, title, brief, content, new Date(), null, null, false, tags,
                        AuthenticationService.getInstance().getSignedInUser(), category);
            }
        }
        return result;
    }

    private String inputTitle(){
        String title = "";
        Scanner scanner = new Scanner(System.in);
        while (title.isEmpty()) {
            System.out.print("\t\u29bf Title: ");
            title = scanner.nextLine();
            if (title.isEmpty())
                System.out.println("\t\t\u26a0 Title Can't Be Empty!");
        }
        return title;
    }
    private Category inputCategory(){
        Category result;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\u29bf Categories: ");
        List<Category> allCategories = CategoryRepository.getInstance().findAll();
        if (allCategories.size() == 0){
            System.out.println("\t\t\u26a0 There is No Category! Add Category As Admin!");
            result = null;
        }
        else {
            for (int i = 0; i < allCategories.size(); i++){
                System.out.println("\t\t" + (i+1) + ". " + allCategories.get(i));
            }

            String chosenNumber = "";
            while ((!IsNumeric.execute(chosenNumber)) || Long.parseLong(chosenNumber) > allCategories.size()) {
                System.out.print("\t\u29bf Choose Category By Number: ");
                chosenNumber = scanner.nextLine();
                if ((!IsNumeric.execute(chosenNumber)) || Long.parseLong(chosenNumber) > allCategories.size())
                    System.out.println("\t\t\u26a0 Invalid Input!");
            }

            result = allCategories.get(Integer.parseInt(chosenNumber) -1);
        }
        return result;
    }
    private List<Tag> inputTags() {
        List<Tag> result = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\u29bf Tags: ");
        List<Tag> allTags = TagRepository.getInstance().findAll();
        if (allTags.size() == 0) {
            System.out.println("\t\t\u26a0 There is No Tag! Add Tags As Admin!");
            result = null;
        }
        else {
            for (int i = 0; i < allTags.size(); i++) {
                System.out.println("\t\t" + (i + 1) + ". " + allTags.get(i));
            }
            result = new ArrayList<>();
            String chosenNumber = "";
            while ((!IsNumeric.execute(chosenNumber)) || Long.parseLong(chosenNumber) > allTags.size()
            || Long.parseLong(chosenNumber) < 0) {
                System.out.print("\t\u29bf Choose Tag By Number:\t(Press 0 to Finish) ");
                chosenNumber = scanner.nextLine();
                if ((!IsNumeric.execute(chosenNumber)) || Long.parseLong(chosenNumber) > allTags.size()
                        || Long.parseLong(chosenNumber) < 0)
                    System.out.println("\t\t\u26a0 Invalid Input!");
                else if (Long.parseLong(chosenNumber) == 0)
                    System.out.println("\t\t\u2705 Adding Tags Finished!");
                else {
                    result.add(allTags.get(Integer.parseInt(chosenNumber) - 1));
                    System.out.println("\t\t\u2705 Tag Added Successfully!");
                }
            }
        }
        return result;
    }
}
