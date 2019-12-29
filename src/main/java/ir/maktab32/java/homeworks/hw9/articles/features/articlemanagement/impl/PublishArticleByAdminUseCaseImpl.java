package ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Article;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.articlemanagement.usecase.PublishArticleByAdminUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.ArticleRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.RoleTitle;

public class PublishArticleByAdminUseCaseImpl implements PublishArticleByAdminUseCase {
    @Override
    public boolean execute(Long id) {
        boolean result;
        if (validation(id)){
            ArticleRepository.getInstance().findById(id).setIsPublished(true);
            System.out.println("Article Published Successfully!");
            result = true;
        }
        else {
            System.out.println("Article Publish Failed!");
            result = false;
        }
        return result;
    }

    private boolean validation(Long id){
        boolean result = true;
        User currentUser = AuthenticationService.getInstance().getSignedInUser();
        if (currentUser == null){
            System.out.println("Please Sign In As Admin!");
            result = false;
        }
        else {
            boolean isAdmin = false;
            for (Role i : currentUser.getRoles()){
                if (i.getTitle().equals(RoleTitle.ADMIN)){
                    isAdmin = true;
                    break;
                }
            }
            if (!isAdmin){
                System.out.println("Please Sign In As Admin!");
                result = false;
            }
            else{
                boolean articleExists = false;
                for (Article i : ArticleRepository.getInstance().findAll()){
                    if (i.getId() == id){
                        articleExists = true;
                        break;
                    }
                }
                if (!articleExists){
                    System.out.println("Requested Article Doesn't Exist!");
                    result = false;
                }
                else {
                    Article requestedArticle = ArticleRepository.getInstance().findById(id);
                    if (requestedArticle.getIsPublished() == true){
                        System.out.println("This Article is Published Already!");
                        result = false;
                    }
                }
            }
        }
        return result;
    }
}
