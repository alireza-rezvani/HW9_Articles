package ir.maktab32.java.homeworks.hw9.articles.features.categorymanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Category;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.categorymanagement.usecase.AddCategoryByAdminUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.CategoryRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.RoleTitle;

public class AddCategoryByAdminUseCaseImpl implements AddCategoryByAdminUseCase {
    @Override
    public Category execute(Category category) {
        Category result;
        if (addCategoryValidation(category)){
            result = CategoryRepository.getInstance().save(category);
            System.out.println("Category Added Successfully!");
        }
        else {
            System.out.println("Add Category Failed!");
            result = null;
        }
        return result;
    }

    private boolean addCategoryValidation(Category category){
        boolean result = true;
        User currentUser = AuthenticationService.getInstance().getSignedInUser();
        if (currentUser == null){
            System.out.println("Please Sign In as Admin!");
            result = false;
        }
        else{
            boolean isAdmin = false;
            for (Role i : currentUser.getRoles())
                if (i.getTitle().equals(RoleTitle.ADMIN)) {
                    isAdmin = true;
                    break;
                }
            if (!isAdmin){
                System.out.println("Please Sign In as Admin!");
                result = false;
            }
            else {
                for (Category i : CategoryRepository.getInstance().findAll())
                    if (i.equals(category)){
                        System.out.println("This Category Already Exists!");
                        result = false;
                    }
            }
        }


        return result;
    }
}
