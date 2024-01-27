package Persistent;

// import java.sql.Connection;
// import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import static Connection.connections.*;

public class appointment {

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

    public void bookAppointment(patient p, doctors d, Scanner sc) {
        System.out.println("Enter Patient Id :");
        int patient_id = sc.nextInt();
        System.out.println("Enter Doctor Id :");
        int doct_id = sc.nextInt();
        System.out.println("Enter Appointment Date :");
        String appointmentDate = sc.next();

        if (p.getPatientById(patient_id) && d.getDoctorById(doct_id)) {

            if (checkDoctorAvailbility(doct_id, appointmentDate)) {
                String sql = "insert into appointment(patient_id,doctor_id,appointment_date) values (?,?,?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, patient_id);
                    ps.setInt(2, doct_id);
                    ps.setString(3, appointmentDate);
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Appointment Booked SuccuessFully");
                    } else {
                        System.out.println("Failed to BooK Appointment !");
                    }

                } catch (Exception e) {
                    e.getMessage();
                }

            } else {
                System.out.println("Doctor Not Available On This Date");

            }

        } else {
            System.out.println("Either Patient or Doctor Does not Exits");
        }

    }

    public boolean checkDoctorAvailbility(int id, String date) {

        String sql = "  SELECT COUNT(*) FROM APPOINTMENT WHERE doctor_id = ? AND appointment_date =? ";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                // String datecount = rs.getString(2);
                if (count == 0) {
                    return true;
                } else {
                    return false;
                }

            }

        } catch (Exception e) {
            e.getMessage();
        }

        return false;

    }

}
