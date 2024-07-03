public class DB {

    private static  String URL="jdbc:postgresql://localhost:5432/database";
    private static  String USER="username";
    private static  String PASS="password";

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        DB.URL = URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static void setUSER(String USER) {
        DB.USER = USER;
    }

    public static String getPASS() {
        return PASS;
    }

    public static void setPASS(String PASS) {
        DB.PASS = PASS;
    }
}
