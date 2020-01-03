package ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.usecase.ChangeUserRoleByAdminUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.RoleRepository;
import ir.maktab32.java.homeworks.hw9.articles.repositories.UserRepository;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;
import ir.maktab32.java.homeworks.hw9.articles.utilities.IsNumeric;

import java.util.Arrays;
import java.util.Scanner;

public class ChangeUserRoleByAdminUseCaseImpl implements ChangeUserRoleByAdminUseCase {
    @Override
    public boolean execute() {
        boolean result;
        User validatedUser = inputAndValidation();
        if (validatedUser != null){
            UserRepository.getInstance().update(validatedUser);
            System.out.println("\t\t\u2705 Roles Edited Successfully!");
            result = true;
        }
        else {
            System.out.println("\t\t\u26a0 Editing Roles Failed!");
            result = false;
        }
        return result;
    }

    private User inputAndValidation(){
        User result;
        Scanner scanner = new Scanner(System.in);
        if (!CurrentUserStatus.isAdmin()){
            System.out.println("\t\u26a0 Sign In As Admin!");
            result = null;
        }
        else {
            System.out.println("\t\u29bf User Id: ");
            String requestedUserId = scanner.nextLine();
            if (!IsNumeric.execute(requestedUserId)) {
                System.out.println("\t\u26a0 Invalid Id!");
                result = null;
            } else if (!UserRepository.getInstance().isExisting(Long.parseLong(requestedUserId))) {
                System.out.println("\t\u26a0 Requested User Doesn't exist!");
                result = null;
            } else {
                User requestedUser = UserRepository.getInstance().findById(Long.parseLong(requestedUserId));
                System.out.println("\t\u2705 Requested User Roles: " + requestedUser.getRoles());
                System.out.println("\n\t1. Add Role to User\n\t2. Delete Role from User");
                String choice = scanner.nextLine();
                if (choice.equals("1")) {
                    System.out.println("\t\u2705 List of Existing Roles: " + RoleRepository.getInstance().findAll());
                    System.out.print("\t\u29bf Choose Role Id to Add: ");
                    String roleId = scanner.nextLine();
                    if (IsNumeric.execute(roleId) && RoleRepository.getInstance().isExisting(Long.parseLong(roleId))
                            && (!requestedUser.getRoles().contains(RoleRepository.getInstance().findById(Long.parseLong(roleId))))) {
                        requestedUser.getRoles().add(RoleRepository.getInstance().findById(Long.parseLong(roleId)));
                        result = requestedUser;
                    } else {
                        System.out.println("\t\t\u26a0 Invalid Input!");
                        result = null;
                    }
                } else if (choice.equals("2")) {
                    System.out.println("\t\u2705 List of Requested User Roles: " + requestedUser.getRoles());
                    System.out.print("\t\u29bf Choose Role Id to Delete: ");
                    String roleId = scanner.nextLine();
                    if (IsNumeric.execute(roleId) && requestedUser.getRoles().size() > 1
                            && (requestedUser.getRoles().contains(RoleRepository.getInstance().findById(Long.parseLong(roleId))))) {
                        requestedUser.getRoles().remove(RoleRepository.getInstance().findById(Long.parseLong(roleId)));
                        result = requestedUser;
                    } else {
                        System.out.println("\t\t\u26a0 Invalid Input!");
                        if (requestedUser.getRoles().size() <= 1)
                            System.out.println("\t\t\u26a0 Each User Must Have One Role At Least!");
                        result = null;
                    }
                } else {
                    System.out.println("\t\u26a0 Invalid Input!");
                    result = null;
                }
            }
        }
        return result;
    }
}
