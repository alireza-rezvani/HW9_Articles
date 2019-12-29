package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.AddArticleByWriterUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.RoleTitle;

public class AddArticleByWriterUseCaseImpl implements AddArticleByWriterUseCase {
    @Override
    public Article execute(Article article) {
        Article result;
        if (addArticleValidation(article)){
            result = ArticleRepository.getInstance().save(article);
            System.out.println("Article Added Successfully!");
        }
        else {
            System.out.println("Article Add Failed!");
            result = null;
        }
        return result;
    }

    private boolean addArticleValidation(Article article){
        boolean result = true;
        User currentUser = AuthenticationService.getInstance().getSignedInUser();
        if (currentUser == null){
            System.out.println("Please Sign In as Writer!");
            result = false;
        }
        else{
            boolean isWriter = false;
            for (Role i : currentUser.getRoles())
                if (i.getTitle().equals(RoleTitle.WRITER)) {
                    isWriter = true;
                    break;
                }
            if (!isWriter){
                System.out.println("Please Sign In as Writer!");
                result = false;
            }
            else {
                for (Article i : ArticleRepository.getInstance().findAll())
                    if (i.equals(article)){
                        System.out.println("This Article Already Exists!");
                        result = false;
                    }
            }
        }


        return result;
    }
}
