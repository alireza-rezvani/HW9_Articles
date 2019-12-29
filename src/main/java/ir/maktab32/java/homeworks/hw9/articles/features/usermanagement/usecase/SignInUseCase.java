package ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.usecase;

import ir.maktab32.java.homeworks.hw9.articles.entities.User;

public interface SignInUseCase {
    User execute(String username, String password);
}
