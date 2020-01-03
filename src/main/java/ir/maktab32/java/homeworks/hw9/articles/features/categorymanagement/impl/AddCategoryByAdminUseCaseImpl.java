package ir.maktab32.java.homeworks.hw9.articles.features.categorymanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Category;
import ir.maktab32.java.homeworks.hw9.articles.features.categorymanagement.usecase.AddCategoryByAdminUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.CategoryRepository;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;

import java.util.List;
import java.util.Scanner;

public class AddCategoryByAdminUseCaseImpl implements AddCategoryByAdminUseCase {
    @Override
    public Category execute() {
        Category result;
        Category validatedCategory = inputAndValidation();
        if (validatedCategory != null){
            CategoryRepository.getInstance().save(validatedCategory);
            System.out.println("\t\t\u2705 Category Saved Successfully!");
            result = validatedCategory;
        }
        else {
            System.out.println("\t\t\u26a0 Adding Category Failed!");
            result = null;
        }
        return result;
    }

    private Category inputAndValidation(){
        Scanner scanner = new Scanner(System.in);

        Category result;
        if (!CurrentUserStatus.isAdmin()){
            System.out.println("\t\u26a0 Sign In As Admin!");
            result = null;
        }
        else {
            String categoryTitle = null;
            while (categoryTitle == null){
                System.out.print("\t\u29bf Category Title: ");
                categoryTitle = scanner.nextLine();
                if (categoryExists(categoryTitle)){
                    System.out.println("\t\t\u26a0 Category Already Exists!");
                    categoryTitle = null;
                }
                else if (categoryTitle.isEmpty()){
                    System.out.println("\t\t\u26a0 Category Title Can't Be Empty!");
                    categoryTitle = null;
                }
            }

            System.out.print("\t\u29bf Category Description: ");
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
