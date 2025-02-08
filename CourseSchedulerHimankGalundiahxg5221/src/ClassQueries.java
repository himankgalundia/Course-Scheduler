/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author himank
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassQueries extends Object {
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getClassSeats;
    private static PreparedStatement getAllCourseCodes;
    private static ResultSet resultSet;
    private static PreparedStatement dropClass;
    
    public static void addClass(ClassEntry Class){
        connection = DBConnection.getConnection();
    
        try{
            addClass = connection.prepareStatement("insert into app.class(semester, coursecode, seats) values(?,?,?)");
            addClass.setString(1, Class.getCourseCode());
            addClass.setString(2,Class.getSemester());
            addClass.setInt(3, Class.getSeats());
            addClass.executeUpdate();
    }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
         
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester){
      connection = DBConnection.getConnection();
      ArrayList<String> results = new ArrayList<>();
      try{
          getAllCourseCodes = connection.prepareStatement("select coursecode from app.class where semester= ?");
          getAllCourseCodes.setString(1, semester);
          resultSet = getAllCourseCodes.executeQuery();
      while (resultSet.next()) {
        results.add(resultSet.getString(1));
      }
      }
      catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }  
    return results;
    }
    
   
    public static int getClassSeats(String semester, String courseCode){
        connection = DBConnection.getConnection();
        try{
            getClassSeats = connection.prepareStatement("select seats from app.class where semester= ? and coursecode=?");
            getClassSeats.setString(1, semester);
            getClassSeats.setString(2, courseCode);
            resultSet = getClassSeats.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
            }
        catch (SQLException sqlException) {
        sqlException.printStackTrace();
        return 0;
        
        }
    }
    
    public static void dropClass(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        try
        {
            dropClass = connection.prepareStatement("delete from app.class where semester = ? and coursecode = ?");
            dropClass.setString(1, semester);
            dropClass.setString(2, courseCode);
            dropClass.executeUpdate();           
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }    
    } 

}
