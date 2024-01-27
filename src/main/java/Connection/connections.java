package Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class connections {
    public static Connection con = null;
    public static String url = "jdbc:mysql://localhost:3306/hospital";
    public static String user = "root";
    public static String password = "root";

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void con() {
        try {
            con = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {

        }
    }
}
