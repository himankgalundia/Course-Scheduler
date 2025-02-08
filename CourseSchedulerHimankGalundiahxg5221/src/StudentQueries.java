/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author himan
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentQueries {
 private static Connection connection;
 private static ArrayList<String> faculty = new ArrayList<String>();
 private static PreparedStatement addStudent;
 private static PreparedStatement getAllStudents;
 private static ResultSet resultSet;   
 private static PreparedStatement dropStudent;  
    
    
 public static void addStudent(StudentEntry student){
     connection = DBConnection.getConnection();
     try{
         addStudent= connection.prepareStatement("insert into app.student (studentID, firstName, lastName) values (?, ?, ?)");
         addStudent.setString(1, student.getStudentID());
         addStudent.setString(2, student.getFirstName());
         addStudent.setString(3, student.getLastName());
         addStudent.executeUpdate();
     
     }
     catch (SQLException sqlException ) {
      sqlException.printStackTrace();
    }
    
 }
    
 public static ArrayList<StudentEntry> getAllStudents(){
     connection = DBConnection.getConnection();
     ArrayList<StudentEntry> results = new ArrayList<>();
     try{
         getAllStudents= connection.prepareStatement("select studentid, firstname, lastname from app.student");
         resultSet = getAllStudents.executeQuery();
         while (resultSet.next()) {
         results.add(new StudentEntry(
          resultSet.getString(1),
          resultSet.getString(2),
          resultSet.getString(3)));
         }
     }
     catch (SQLException sqlException ) {
      sqlException.printStackTrace();
    }
    return results;
 }
 
 public static StudentEntry getStudent(String studentID) {
        connection = DBConnection.getConnection();
        StudentEntry student = null;
        try
        {
            addStudent = connection.prepareStatement("select firstname, lastname from app.student where studentid = ? ");
            addStudent.setString(1, studentID);
            resultSet = addStudent.executeQuery();
                                    
            if(resultSet.next())
            {    
                student = new StudentEntry(studentID, resultSet.getString(1), resultSet.getString(2));               
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }            
        
        return student;
        
    } 
 
 public static void dropStudent(String studentID) {
        connection = DBConnection.getConnection();
        try
        {
            dropStudent = connection.prepareStatement("delete from app.student where studentid = ?");
            dropStudent.setString(1, studentID);
            dropStudent.executeUpdate();           
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    } 
 
 
}
