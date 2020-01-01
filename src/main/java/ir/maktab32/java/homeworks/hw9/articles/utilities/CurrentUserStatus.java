package ir.maktab32.java.homeworks.hw9.articles.utilities;

import ir.maktab32.java.homeworks.hw9.articles.entities.Role;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;
import ir.maktab32.java.homeworks.hw9.articles.share.AuthenticationService;

public class CurrentUserStatus {
    public static boolean isSignedIn(){
        boolean result = false;
        if (AuthenticationService.getInstance().getSignedInUser() != null)
            result = true;
        return result;
    }
    public static boolean isWriter(){
        boolean result = false;
        User currentUser = AuthenticationService.getInstance().getSignedInUser();
        if (currentUser == null)
            result = false;
        else {
            for (Role i : currentUser.getRoles()){
                if (i.getTitle().equals(RoleTitle.WRITER)){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    public static boolean isAdmin(){
        boolean result = false;
        User currentUser = AuthenticationService.getInstance().getSignedInUser();
        if (currentUser == null)
            result = false;
        else {
            for (Role i : currentUser.getRoles()){
                if (i.getTitle().equals(RoleTitle.ADMIN)){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
