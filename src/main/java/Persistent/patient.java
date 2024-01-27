package Persistent;

// import static Connection.connections.url;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.sql.Connection;
// import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connection.connections;

import static Connection.connections.*;

public class patient {

    static {
        // connections.con();
        connections.con();

    }

    // private static Connection con = null;
    // private static String url = "jdbc:mysql://localhost:3306/hospital";
    // private static String user = "root";
    // private static String password = "root";

    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // static {
    // try {
    // con = DriverManager.getConnection(url, user, password);

    // } catch (Exception e) {

    // }
    // }

    public void addPatient() {
        try (PreparedStatement psmt = con.prepareStatement("insert into patients(name,age,gender) values(?,?,?)")) {
            System.out.println("Enter Patient Name: ");
            String name = br.readLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt(br.readLine());
            System.out.println("Enter Gender :");
            String gender = br.readLine();

            psmt.setString(1, name);
            psmt.setInt(2, age);
            psmt.setString(3, gender);
            int r = psmt.executeUpdate();
            System.out.println(r + " Rows Affected SuccussFully Added Press V to view");
            String ans = br.readLine();
            if (ans.equals("v") || ans.equals("V")) {
                viewPatient();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewPatient() {
        try {
            PreparedStatement psmt = con.prepareStatement("Select * from patients");
            ResultSet rs = psmt.executeQuery();
            System.out.println("+----+-------+-----+--------+");
            System.out.println("| id | name  | age | gender |");
            System.out.println("+----+-------+-----+--------+");
            while (rs.next()) {

                System.out.printf("| %-2s | %-5s | %-3s | %-6s |", rs.getInt("id"), rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"));
                System.out.println();
                System.out.println("+----+-------+-----+--------+");
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean getPatientById(int id) {
        try (PreparedStatement psmt = con.prepareStatement("select * from patients where id =?")) {
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
