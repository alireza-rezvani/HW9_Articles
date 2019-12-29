package ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.usecase.SignUpUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.UserRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.IsNumeric;

import java.util.List;

public class SignUpUseCaseImpl implements SignUpUseCase {
    @Override
    public User execute(User user) {
        User result;
        if (signUpValidation(user)){
            User createdUser = UserRepository.getInstance().save(user);
            System.out.println("User Created Successfully! User Id: " + createdUser.getId());
            result = createdUser;
        }
        else {
            System.out.println("User Creation Failed");
            result = null;
        }
        return result;
    }

    private boolean signUpValidation(User user){
        boolean result = true;
        if (AuthenticationService.getInstance().getSignedInUser() != null){
            System.out.println("Another User is Signed In! Sign Out First!");
            result = false;
        }

        List<User> users = UserRepository.getInstance().findAll();
        for (User i : users)
            if (i.getUsername().equalsIgnoreCase(user.getUsername())){
                System.out.println("This Username Already Exists!");
                result = false;
                break;
            }
        for (User i : users)
            if (i.getId() == user.getId()){
                System.out.println("This User Id Already Exists!");
                result = false;
                break;
            }

        if ((!IsNumeric.execute(user.getNationalCode())) || (user.getNationalCode().length() != 10)){
            System.out.println("Invalid National Code!");
            result = false;
        }

        return result;
    }
}
