package ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.usecase.ChangePasswordByUserUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.UserRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;

import java.util.Scanner;

public class ChangePasswordByUserUseCaseImpl implements ChangePasswordByUserUseCase {
    @Override
    public boolean execute() {
        boolean result;
        User validatedUser = inputAndValidation();
        if (validatedUser != null){
            UserRepository.getInstance().update(validatedUser);
            System.out.println("password changed successfully");
            result = true;
        }
        else {
            System.out.println("password change failed");
            result = false;
        }
        return result;
    }

    private User inputAndValidation(){
        Scanner scanner = new  Scanner(System.in);

        User result;
        if (CurrentUserStatus.isSignedIn()) {
            System.out.println("New Password: ");
            String newPassword = scanner.nextLine();

            result = AuthenticationService.getInstance().getSignedInUser();
            result.setPassword(newPassword);
        }
        else {
            System.out.println("Sign in first");
            result = null;
        }
        return result;
    }
}
