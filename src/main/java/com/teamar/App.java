package com.teamar;

import Persistent.patient;

import java.util.Scanner;

import Persistent.appointment;
import Persistent.doctors;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        patient patient = new patient();
        doctors doctors = new doctors();
        appointment appointment = new appointment();
        // patient.addPatient();
        // p.viewPatient();
        // p.viewPatient();
        // p.getPatientById(2);
        // doctors d = new doctors();
        // d.viewDoctors();
        // System.out.println(d.getDoctorById(1));

        while (true) {
            System.out.println("............HOSPITAL MANAGEMENT SYSTEM..............");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. View Doctors");
            System.out.println("4. Book Appointment");
            System.out.println("5. Exit");
            System.out.println("Enter Your Choice");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // add Patient
                    patient.addPatient();

                    break;
                case 2:
                    // view Patient
                    patient.viewPatient();
                    break;
                case 3:
                    // View Doctors
                    doctors.viewDoctors();
                    break;
                case 4:
                    // Book
                    appointment.bookAppointment(patient, doctors, sc);
                    break;
                case 5:
                    return;

                default:
                    System.out.println("Enter A Valid Choice");
            }

        }

    }
}
