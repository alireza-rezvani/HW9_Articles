package ir.maktab32.java.homeworks.hw9.articles.menu;

import ir.maktab32.java.homeworks.hw9.articles.features.dashboard.impl.GetDashboardByAdminUseCaseImpl;
import ir.maktab32.java.homeworks.hw9.articles.features.dashboard.impl.GetDashboardByWriterUseCaseImpl;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;

public class DashboardMenu {
    public static void execute(){

        if (!CurrentUserStatus.isSignedIn()){
            System.out.println("\t\u26a0 Sign In First!");
        }
        else {
            if (CurrentUserStatus.isWriter()) {
                System.out.println("\u29bf Writer Dashboard!");
                System.out.println(new GetDashboardByWriterUseCaseImpl().execute());
            }
            if (CurrentUserStatus.isAdmin()){
                System.out.println("\u29bf Admin Dashboard!");
                System.out.println(new GetDashboardByAdminUseCaseImpl().execute());
            }
        }
    }
}
