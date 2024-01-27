package Persistent;

// import java.sql.Connection;
// import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static Connection.connections.*;

public class doctors {
    static {
        con();
    }
    // private static Connection con = null;
    // private static String url = "jdbc:mysql://localhost:3306/hospital";
    // private static String user = "root";
    // private static String password = "root";

    // static {
    // try {
    // con = DriverManager.getConnection(url, user, password);

    // } catch (Exception e) {

    // }
    // }

    public void viewDoctors() {
        try {
            PreparedStatement psmt = con.prepareStatement("select * from doctors");
            ResultSet rs = psmt.executeQuery();
            System.out.println("+----+-------+--------------+");
            System.out.println("| id | name  |specialization|");
            System.out.println("+----+-------+--------------+");

            while (rs.next()) {
                System.out.printf("| %-2s | %-5s | %-12s |", rs.getInt(1), rs.getString(2), rs.getString(3));
                System.out.println();
                System.out.println("+----+-------+-----+--------+");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean getDoctorById(int id) {
        try (PreparedStatement psmt = con.prepareStatement("select * from doctors where id =?")) {
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

}
