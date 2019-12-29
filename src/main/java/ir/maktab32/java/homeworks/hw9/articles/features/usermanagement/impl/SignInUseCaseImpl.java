package ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.usecase.SignInUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.UserRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;

import java.util.List;

public class SignInUseCaseImpl implements SignInUseCase {
    @Override
    public User execute(String username, String password) {
        User result = null;
        if (signInValidation(username, password)){
            for (User i : UserRepository.getInstance().findAll())
                if (i.getUsername().equalsIgnoreCase(username)){
                    result = i;
                    AuthenticationService.getInstance().setSignedInUser(result);
                    System.out.println("Sign In Successful!");
                    break;
                }

        }
        else {
            System.out.println("Sign In Failed!");
            result = null;
        }
        return result;
    }

    private boolean signInValidation(String username, String password){
        boolean result = true;
        List<User> users = UserRepository.getInstance().findAll();
        boolean userExists = false;
        User userFromDataBase = null;
        for (User i : users)
            if (i.getUsername().equalsIgnoreCase(username)){
                userExists = true;
                userFromDataBase = i;
                break;
            }

        if (!userExists){
            System.out.println("There is not Such User!");
            result = false;
        }
        else if (!userFromDataBase.getPassword().equals(password)){
            System.out.println("Invalid Password!");
            result = false;
        }

        return result;
    }
}
