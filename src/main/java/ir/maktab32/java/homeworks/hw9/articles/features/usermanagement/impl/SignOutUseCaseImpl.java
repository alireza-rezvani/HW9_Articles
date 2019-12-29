package ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.usecase.SignOutUseCase;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;

public class SignOutUseCaseImpl implements SignOutUseCase {
    @Override
    public boolean execute() {
        boolean result = false;
        if (signOutValidation()){
            AuthenticationService.getInstance().setSignedInUser(null);
            result = true;
            System.out.println("Sign Out Successful!");
        }
        else
            System.out.println("Sign Out Failed!");
        return result;
    }

    private boolean signOutValidation(){
        if (AuthenticationService.getInstance().getSignedInUser() == null){
            System.out.println("You Are not Signed In!");
            return false;
        }
        else
            return true;
    }
}
