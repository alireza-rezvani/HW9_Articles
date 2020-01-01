package ir.maktab32.java.homeworks.hw9.articles.utilities;

public enum RoleTitle {
    ADMIN("Admin"), WRITER("Writer");

    private String value;
    RoleTitle(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
