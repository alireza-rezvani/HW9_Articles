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
            System.out.println("\t\t\u2705 Tag Saved Successfully!");
            result = validatedTag;
        }
        else {
            System.out.println("\t\t\u26a0 Saving Tag Failed!");
            result = null;
        }
        return result;
    }

    private Tag inputAndValidation(){
        Scanner scanner = new Scanner(System.in);
        Tag result;
        if (CurrentUserStatus.isAdmin()){
            System.out.print("\t\u29bf Tag Title: ");
            String tag = scanner.nextLine();
            boolean tagExist = false;
            for (Tag i : TagRepository.getInstance().findAll()){
                if (i.getTitle().equals(tag)){
                    tagExist = true;
                    break;
                }
            }
            if (tagExist){
                System.out.println("\t\t\u26a0 This Tag Already Exists!");
                result = null;
            }
            else {
                result = new Tag(null,tag,null);
            }
        }
        else {
            System.out.println("\t\u26a0 Sign In As Admin!");
            result = null;
        }

        return result;
    }
}
