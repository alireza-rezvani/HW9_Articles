package ir.maktab32.java.homeworks.hw9.articles.features.tagmanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Tag;
import ir.maktab32.java.homeworks.hw9.articles.features.tagmanagement.usecase.AddTagByAdminUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.TagRepository;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;

import java.util.Scanner;

public class AddTagByAdminUseCaseImpl implements AddTagByAdminUseCase {
    @Override
    public Tag execute() {
        Tag result;
        Tag validatedTag = inputAndValidation();
        if (validatedTag != null){
            TagRepository.getInstance().save(validatedTag);
            System.out.println("tag saved");
            result = validatedTag;
        }
        else {
            System.out.println("saving tag failed");
            result = null;
        }
        return result;
    }

    private Tag inputAndValidation(){
        Scanner scanner = new Scanner(System.in);
        Tag result;
        if (CurrentUserStatus.isAdmin()){
            System.out.print("tag title: ");
            String tag = scanner.nextLine();
            boolean tagExist = false;
            for (Tag i : TagRepository.getInstance().findAll()){
                if (i.getTitle().equals(tag)){
                    tagExist = true;
                    break;
                }
            }
            if (tagExist){
                System.out.println("this tag already exists");
                result = null;
            }
            else {
                result = new Tag(null,tag,null);
            }
        }
        else {
            System.out.println("sign in as admin");
            result = null;
        }

        return result;
    }
}
