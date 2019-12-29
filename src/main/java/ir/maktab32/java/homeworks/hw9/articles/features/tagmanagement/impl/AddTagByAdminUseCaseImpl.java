package ir.maktab32.java.homeworks.hw9.articles.features.tagmanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Tag;
import ir.maktab32.java.homeworks.hw9.articles.features.tagmanagement.usecase.AddTagByAdminUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.TagRepository;

public class AddTagByAdminUseCaseImpl implements AddTagByAdminUseCase {
    @Override
    public Tag add(Tag tag) {
        Tag result = null;
        if (addTagValidation(tag)){
            TagRepository.getInstance().save(tag);
            System.out.println("Tag Added Successfully!");
            result = tag;
        }
        else {
            System.out.println("Adding Tag Failed!");
            result = null;
        }
        return result;
    }

    private boolean addTagValidation(Tag tag){
        boolean result = true;
        if (TagRepository.getInstance().findAll().contains(tag)){
            System.out.println("This Tag Already Exists in Database!");
            result = false;
        }

        if (tag.getTitle().isEmpty()){
            System.out.println("Tag Title Can't Be Empty!");
            result = false;
        }
        return result;
    }
}
