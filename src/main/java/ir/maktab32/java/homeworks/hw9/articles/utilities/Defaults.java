package ir.maktab32.java.homeworks.hw9.articles.utilities;

import ir.maktab32.java.homeworks.hw9.articles.entities.Category;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.Tag;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.repositories.CategoryRepository;
import ir.maktab32.java.homeworks.hw9.articles.repositories.RoleRepository;
import ir.maktab32.java.homeworks.hw9.articles.repositories.TagRepository;
import ir.maktab32.java.homeworks.hw9.articles.repositories.UserRepository;

import java.util.Arrays;

public class Defaults {
    public static void execute(){
        if (RoleRepository.getInstance().findAll().size() == 0){
            RoleRepository.getInstance().save(new Role(null,RoleTitle.ADMIN,null));
            RoleRepository.getInstance().save(new Role(null, RoleTitle.WRITER, null));
        }

        if (!UserRepository.getInstance().isExisting(1l)){
            User defaultAdmin = new User(null,"admin1","1",
                    null, null, Arrays.asList(RoleRepository.getInstance().findById(1l)));
            UserRepository.getInstance().save(defaultAdmin);
        }

        if (TagRepository.getInstance().findAll().size() == 0){
            TagRepository.getInstance().save(new Tag(null, "tag1", null));
        }

        if (CategoryRepository.getInstance().findAll().size() == 0){
            CategoryRepository.getInstance().save(new Category(null, "category1", "description1"));
        }
    }
}
