package ir.maktab32.java.homeworks.hw9.articles.features.categorymanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Category;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.categorymanagement.usecase.AddCategoryByAdminUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.CategoryRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;
import ir.maktab32.java.homeworks.hw9.articles.utilities.RoleTitle;

import java.util.List;
import java.util.Scanner;

public class AddCategoryByAdminUseCaseImpl implements AddCategoryByAdminUseCase {
    @Override
    public Category execute() {
        Category result;
        Category validatedCategory = inputAndValidation();
        if (validatedCategory != null){
            CategoryRepository.getInstance().save(validatedCategory);
            System.out.println("Category Saved!");
            result = validatedCategory;
        }
        else {
            System.out.println("Adding Category Failed!");
            result = null;
        }
        return result;
    }

    private Category inputAndValidation(){
        Scanner scanner = new Scanner(System.in);

        Category result;
        if (!CurrentUserStatus.isAdmin()){
            System.out.println("sign in as admin");
            result = null;
        }
        else {
            String categoryTitle = null;
            while (categoryTitle == null){
                System.out.print("category title: ");
                categoryTitle = scanner.nextLine();
                if (categoryExists(categoryTitle)){
                    System.out.println("category already exists");
                    categoryTitle = null;
                }
                else if (categoryTitle.isEmpty()){
                    System.out.println("category title cant be empty");
                    categoryTitle = null;
                }
            }

            System.out.println("category description: ");
            String categoryDescription = scanner.nextLine();
            result = new Category(null,categoryTitle, categoryDescription);
        }
        return result;
    }

    private boolean categoryExists(String categoryTitle){
        boolean result = false;
        List<Category> allCategories = CategoryRepository.getInstance().findAll();
        if (allCategories.size() > 0){
            for (Category i : allCategories){
                if (i.getTitle().equals(categoryTitle)){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
