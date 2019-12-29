package ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.usecase.ChangePasswordByUserUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.UserRepository;

public class ChangePasswordByUserUseCaseImpl implements ChangePasswordByUserUseCase {
    @Override
    public boolean execute(User user, String newPassword) {
        boolean result;
        if (changePasswordValidation(user, newPassword)){
            user.setPassword(newPassword);
            UserRepository.getInstance().update(user);
            result = true;
            System.out.println("Password Changed Successfully!");
        }
        else {
            System.out.println("Password Change Failed!");
            result = false;
        }
        return result;
    }

    private boolean changePasswordValidation(User user, String newPassword){
        boolean result = true;
        if (!UserRepository.getInstance().findAll().contains(user)){
            System.out.println("This User Doesn't Exist in Database!");
            result = false;
        }
        if (newPassword.isEmpty()){
            System.out.println("Password Can't Be Empty!");
            result = false;
        }
        return result;
    }
}
