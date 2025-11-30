package HospitalManagementSystem;

import javax.print.Doc;
import java.sql.*;
import java.util.Scanner;
//import java.sql.Connection;

import java.sql.SQLException;
public class HospitalManagementSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "9424224082Hp@"; // mask it before commiting

    public static boolean checkavailability(Connection connection, int doctor_id, String appointment_date){
        String query = "Select count(*) from appointments where doctor_id = ? and appointment_date = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,doctor_id);
            preparedStatement.setString(2,appointment_date);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void bookAppointment(Connection connection, Scanner scanner, Patient patient, Doctor doctor){
        System.out.print("Enter Patient Id: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor Id: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();
        if(patient.checkPatient(patientId) && doctor.checkDoctor(doctorId)){
            if(checkavailability(connection, doctorId, appointmentDate)){
                String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointmentDate);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected>0){
                        System.out.println("Appointment Booked!");
                    }else{
                        System.out.println("Failed to Book Appointment!");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("Doctor not available on this date!!");
            }
        }else{
            System.out.println("Either doctor or patient doesn't exist!!!");
        }
    }

    public static void bookappointment2(Connection connection, Scanner scanner, Patient patient, Doctor doctor){
        System.out.print("Enter Patient Id");
        int patient_id = scanner.nextInt();
        System.out.print("Enter Doctor Id");
        int doctor_id = scanner.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();
        if(patient.checkPatient(patient_id) && doctor.checkDoctor(doctor_id)){
            if(checkavailability(connection, doctor_id, appointmentDate)){
                String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patient_id);
                    preparedStatement.setInt(2, doctor_id);
                    preparedStatement.setString(3, appointmentDate);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected>0){
                        System.out.println("Appointment Booked!");
                    }else{
                        System.out.println("Failed to Book Appointment!");
                    }
            }
                catch (SQLException e){
                    e.printStackTrace();
                }}
            else{
                System.out.println("Doctor not available on this date!!");
            }
        }
        else{
            System.out.println("Either doctor or patient doesn't exist!!!");
        }
    }

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Scanner scanner = new Scanner(System.in);
            Connection connection =  DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);
            while(true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM ");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();

                switch(choice){
                    case 1:
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3:
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case 4:
                        bookAppointment(connection, scanner, patient, doctor);
                        System.out.println();
                        break;
                    case 5:
                        System.out.println("THANK YOU! FOR USING HOSPITAL MANAGEMENT SYSTEM!!");
                        return;
                    default:
                        System.out.print("Enter a valid choice.");
                        break;
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
