//package ir.maktab32.java.homeworks.hw9.articles.features.categorymanagement.impl;
//
//import ir.maktab32.java.homeworks.hw9.articles.entities.Category;
//import ir.maktab32.java.homeworks.hw9.articles.features.categorymanagement.usecase.FindCategoryBasedOnIdByApplicationUseCase;
//import ir.maktab32.java.homeworks.hw9.articles.repositories.CategoryRepository;
//
//public class FindCategoryBasedOnIdByApplicationUseCaseImpl implements FindCategoryBasedOnIdByApplicationUseCase {
//    @Override
//    public Category execute(Long id) {
//        Category result;
//        if (validation(id)){
//            result = CategoryRepository.getInstance().findById(id);
//            System.out.println("Category Loaded Successfully!");
//        }
//        else {
//            System.out.println("Loading Category Failed!");
//            result = null;
//        }
//        return result;
//    }
//
//    private boolean validation(Long id){
//        boolean result = true;
//        boolean categoryExist = false;
//        for (Category i : CategoryRepository.getInstance().findAll()){
//            if (i.getId() == id){
//                categoryExist = true;
//                break;
//            }
//        }
//        if (!categoryExist){
//            System.out.println("Requseted Category Doesn't Exist!");
//            result = false;
//        }
//        return result;
//    }
//}
