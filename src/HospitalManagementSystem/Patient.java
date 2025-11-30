package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Scanner scanner;
    private Connection connection;

    Patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient(){
        System.out.print("Enter Patient Name");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter Patient Age");
        int age = scanner.nextInt();
        System.out.print("Enter Patient Gender");
        String gender = scanner.next();

        try{
            String query = "INSERT INTO PATIENTS (name, age, gender) VALUES(?, ?, ?)";
            PreparedStatement preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1,name);
            preparedstatement.setInt(2, age);
            preparedstatement.setString(3,gender);
            int affectedrows = preparedstatement.executeUpdate();
            if(affectedrows>0){
                System.out.print("Patient Added Successfully");
            }
            else{
                System.out.print("Failed to add Patient.");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void viewPatients(){
        String query = "Select * from Patients";
        try{
            PreparedStatement preparedstatement = connection.prepareStatement(query);
            ResultSet rs = preparedstatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------------+--------------------+----------+------------+");
            System.out.println("| Patient Id | Name               | Age      | Gender     |");
            System.out.println("+------------+--------------------+----------+------------+");
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                System.out.printf("| %-10s | %-18s | %-8s | %-10s |\n", id, name, age, gender);
                System.out.println("+------------+--------------------+----------+------------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public boolean checkPatient(int id){
        String query = "Select * from patients where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;

    }



}
