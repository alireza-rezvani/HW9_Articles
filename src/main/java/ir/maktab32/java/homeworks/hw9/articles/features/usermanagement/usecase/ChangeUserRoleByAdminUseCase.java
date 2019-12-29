package ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.usecase;

import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;

public interface ChangeUserRoleByAdminUseCase {
    boolean execute(User user, Role newRole);
}
