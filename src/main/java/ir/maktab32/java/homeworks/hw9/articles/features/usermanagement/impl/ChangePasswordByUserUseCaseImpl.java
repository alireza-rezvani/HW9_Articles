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
            System.out.println("\t\t\u2705 Password Changed Successfully!");
            result = true;
        }
        else {
            System.out.println("\t\t\u26a0 Password Change Failed!");
            result = false;
        }
        return result;
    }

    private User inputAndValidation(){
        Scanner scanner = new  Scanner(System.in);

        User result;
        if (CurrentUserStatus.isSignedIn()) {
            System.out.print("\t\u29bf New Password: ");
            String newPassword = scanner.nextLine();

            result = AuthenticationService.getInstance().getSignedInUser();
            result.setPassword(newPassword);
        }
        else {
            System.out.println("\t\u26a0 Sign In First!");
            result = null;
        }
        return result;
    }
}
