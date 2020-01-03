package ir.maktab32.java.homeworks.hw9.articles.menu;

import ir.maktab32.java.homeworks.hw9.articles.utilities.CurrentUserStatus;

import java.util.Scanner;

public class CommandsMenu {
    private static Scanner scanner = new Scanner(System.in);
    public static void execute(){

        boolean isSignedIn = CurrentUserStatus.isSignedIn();
        boolean isAdmin = CurrentUserStatus.isAdmin();
        boolean isWriter = CurrentUserStatus.isWriter();

        System.out.println("+-------------------------------------------+");
        System.out.println("|                     Menu                  |");
        System.out.println("+-------------------------------------------+");
        if (!isSignedIn){
            System.out.println("| sign in        -->  Sign In               |");
            System.out.println("| sign up        -->  Sign Up               |");
        }
        System.out.println("| all articles   -->  all Articles          |");
        if (isWriter){
            System.out.println("| my articles    -->  My Articles           |");
            System.out.println("| add article    -->  Add Article           |");
            System.out.println("| edit article   -->  Edit Article          |");
        }
        if (isAdmin){
            System.out.println("| change role    -->  Change User Roles     |");
            System.out.println("| add tag        -->  Add New Tags          |");
            System.out.println("| add category   -->  Add New Categories    |");
            System.out.println("| delete article -->  Delete  Article       |");
            System.out.println("| publish        -->  Publish Article       |");
            System.out.println("| unpublish      -->  UnPublish Article     |");
        }
        System.out.println("| search         -->  Search Articles       |");
        if (isSignedIn){
            System.out.println("| dashboard      -->  Dashboard             |");
            System.out.println("| change pass    -->  Change Password       |");
            System.out.println("| sign out       -->  Sign Out              |");
        }
        System.out.println("+-------------------------------------------+");
    }
}
