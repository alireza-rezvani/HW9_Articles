package ir.maktab32.java.homeworks.hw9.articles.menu;

import ir.maktab32.java.homeworks.hw9.articles.features.dashboard.impl.GetDashboardByAdminUseCaseImpl;
import ir.maktab32.java.homeworks.hw9.articles.features.dashboard.impl.GetDashboardByWriterUseCaseImpl;
import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;

public class DashboardMenu {
    public static void execute(){

        if (!CurrentUserStatus.isSignedIn()){
            System.out.println("sign in first");
        }
        else if (CurrentUserStatus.isWriter())
            System.out.println(new GetDashboardByWriterUseCaseImpl());
        else if (CurrentUserStatus.isAdmin())
            System.out.println(new GetDashboardByAdminUseCaseImpl());
    }
}
