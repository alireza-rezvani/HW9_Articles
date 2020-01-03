package ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.impl;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.features.usermanagement.usecase.SignUpUseCase;
import ir.maktab32.java.homeworks.hw9.articles.repositories.RoleRepository;
import ir.maktab32.java.homeworks.hw9.articles.repositories.UserRepository;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;
import ir.maktab32.java.homeworks.hw9.articles.utilities.IsNumeric;
import ir.maktab32.java.homeworks.hw9.articles.utilities.RoleTitle;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SignUpUseCaseImpl implements SignUpUseCase {
    @Override
    public User execute() {
        User result;
        User validateResult = inputAndValidation();
        if (validateResult != null){
            User createdUser = UserRepository.getInstance().save(validateResult);
            System.out.println("\t\t\t\u2705User Created Successfully! User Id: " + createdUser.getId() + " (Password: National Code)");
            result = createdUser;
        }
        else {
            System.out.println("\t\t\t\u26a0 User Creation Failed!");
            result = null;
        }
        return result;
    }

    private User inputAndValidation(){
        Scanner scanner = new Scanner(System.in);

        User result = null;

        if (CurrentUserStatus.isSignedIn())
            System.out.println("\t\u26a0 Another User is Signed In! Sign Out First!");
        else {
            String username = inputUsername();
            String nationalCode = inputNationalCode();
            System.out.print("\t\u29bf Birth Date: ");
            String birthDate = scanner.nextLine();
            Role writerRole = null;
            List<Role> allRoles = RoleRepository.getInstance().findAll();
            for (Role i : allRoles){
                if (i.getTitle().equals(RoleTitle.WRITER)){
                    writerRole = i;
                    break;
                }
            }
            if (writerRole == null)
                System.out.println("\t\t\u26a0 Add Writer Role to Database As Admin!");

            if (username != null && nationalCode != null && writerRole != null){
                result = new User(null, username, nationalCode, nationalCode, birthDate, Arrays.asList(writerRole));
            }
        }
        return result;
    }
    private String inputUsername(){
        String username = null;

        Scanner scanner = new Scanner(System.in);
        List<User> allUsers = UserRepository.getInstance().findAll();
        while (username == null){
            System.out.print("\t\u29bf Username: ");
            username = scanner.nextLine();
            if (username.isEmpty()){
                System.out.println("\t\t\u26a0 Username Can't Be Empty!");
                username = null;
            }
            else {
                if (allUsers.size() > 0) {
                    for (User i : allUsers) {
                        if (i.getUsername().equals(username)) {
                            System.out.println("\t\t\u26a0 This Username Already Exists!");
                            username = null;
                            break;
                        }
                    }
                }
            }
        }
        return username;
    }
    private String inputNationalCode(){
        Scanner scanner = new Scanner(System.in);

        String nationalCode = null;
        while (nationalCode == null){
            System.out.print("\t\u29bf National Code: ");
            nationalCode = scanner.nextLine();

            if ((!IsNumeric.execute(nationalCode)) || nationalCode.length() != 10){
                System.out.println("\t\t\u26a0 Invalid National Code!");
                nationalCode = null;
            }
        }
        return nationalCode;
    }
}
