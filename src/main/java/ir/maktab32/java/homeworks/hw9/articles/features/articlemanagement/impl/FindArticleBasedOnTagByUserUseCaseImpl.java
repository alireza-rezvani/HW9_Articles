package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.entities.Tag;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.FindArticleBasedOnTagByUserUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.repositories.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindArticleBasedOnTagByUserUseCaseImpl implements FindArticleBasedOnTagByUserUseCase {

    //another solution
    //we can also find articles based on tags using many to many

    @Override
    public List<Article> execute() {
        List<Article> result;
        Tag validatedTag = inputAndValidation();
        if (validatedTag != null){
            List<Article> allArticles = ArticleRepository.getInstance().findAll();
            result = new ArrayList<>();
            for (Article i : allArticles){
                if (i.getTags().contains(validatedTag))
                    result.add(i);
            }
            if (result.size() != 0)
                System.out.println("\t\t\u2705 Articles Found Successfully!");
            else {
                System.out.println("\t\t\u26a0 There is No Article fot This Tag!");
                result = null;
            }
        }
        else {
            System.out.println("\t\t\u26a0 Articles Loading Failed!");
            result = null;
        }
        return result;
    }

    private Tag inputAndValidation(){
        Scanner scanner = new Scanner(System.in);
        Tag result;
        System.out.print("\t\u29bf Tag Title: ");
        String tagTitle = scanner.nextLine();
        Tag requestedTag = null;
        for (Tag i : TagRepository.getInstance().findAll()){
            if (i.getTitle().equals(tagTitle)){
                requestedTag = i;
                break;
            }
        }
        if (requestedTag == null){
            System.out.println("\t\t\u26a0 Requested Tag Doesn't Exist!");
            result = null;
        }
        else {
            result = requestedTag;
        }
        return result;
    }
}
