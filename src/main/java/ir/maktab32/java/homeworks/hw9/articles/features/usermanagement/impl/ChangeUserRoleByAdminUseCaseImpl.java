package ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.usecase.ChangeUserRoleByAdminUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.RoleRepository;
import ir.maktab32.java.homeworks.hw9.articles.repositories.UserRepository;

import java.util.Arrays;

public class ChangeUserRoleByAdminUseCaseImpl implements ChangeUserRoleByAdminUseCase {
    @Override
    public boolean execute(User user, Role newRole) {
        boolean result;
        if (changeRoleValidation(user, newRole)){
            user.setRoles(Arrays.asList(newRole));
            UserRepository.getInstance().update(user);
            System.out.println("New Role Was Set Successfully!");
            result = true;
        }
        else {
            System.out.println("Role Change Failed!");
            result = false;
        }
        return result;
    }

    private boolean changeRoleValidation(User user, Role newRole){
        boolean result = true;

        if (!UserRepository.getInstance().findAll().contains(user)){
            System.out.println("This User Doesn't Exist!");
            result = false;
        }
        if (!RoleRepository.getInstance().findAll().contains(newRole)){
            System.out.println("Entered Role Doesn't Exist in Database!");
            result = false;
        }
        return result;
    }
}
