//package ir.maktab32.java.homeworks.hw9.articles.features.tagmanagement.impl;
//
//import ir.maktab32.java.homeworks.hw9.articles.entities.Tag;
//import ir.maktab32.java.homeworks.hw9.articles.features.tagmanagement.usecase.FindAllTagsByApplicationUseCase;
//import ir.maktab32.java.homeworks.hw9.articles.repositories.TagRepository;
//
//import java.util.List;
//
//public class FindAllTagsByApplicationUseCaseImpl implements FindAllTagsByApplicationUseCase {
//    @Override
//    public List<Tag> execute() {
//        List<Tag> result;
//        if (validation()){
//                result = TagRepository.getInstance().findAll();
//            System.out.println("tags loaded successfully");
//
//        }
//        else {
//            System.out.println("Loading Tags Failed!");
//            result = null;
//        }
//        return result;
//    }
//
//    private boolean validation(){
//        boolean result = true;
//        if (TagRepository.getInstance().findAll().size() == 0){
//            System.out.println("Tag List is Empty!");
//            result = false;
//        }
//        return result;
//    }
//}
