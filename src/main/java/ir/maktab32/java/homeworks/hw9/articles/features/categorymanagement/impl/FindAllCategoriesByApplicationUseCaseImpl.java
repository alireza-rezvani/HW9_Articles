//package ir.maktab32.java.homeworks.hw9.articles.features.categorymanagement.impl;
//
//import ir.maktab32.java.homeworks.hw9.articles.entities.Category;
//import ir.maktab32.java.homeworks.hw9.articles.features.categorymanagement.usecase.FindAllCategoriesByApplicationUseCase;
//import ir.maktab32.java.homeworks.hw9.articles.repositories.CategoryRepository;
//
//import java.util.List;
//
//public class FindAllCategoriesByApplicationUseCaseImpl implements FindAllCategoriesByApplicationUseCase {
//    @Override
//    public List<Category> list() {
//        List<Category> result;
//        if (validation()){
//            result = CategoryRepository.getInstance().findAll();
//            System.out.println("Categories Loaded Successfully!");
//
//        }
//        else {
//            System.out.println("Loading Categories Failed!");
//            result =null;
//        }
//        return result;
//    }
//
//    private boolean validation(){
//        boolean result = true;
//        if (CategoryRepository.getInstance().findAll().size() == 0){
//            System.out.println("There is No Category In Database!");
//            result = false;
//        }
//        return result;
//    }
//}
