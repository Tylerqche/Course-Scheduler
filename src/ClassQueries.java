import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author tyler
 */
public class ClassQueries {
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getClassList;
    private static ResultSet resultSet;
    
    public static void addClass(ClassEntry classEntry) {
        Connection connection = null;
        PreparedStatement addClass = null;

        try {
            connection = DBConnection.getConnection();

            addClass = connection.prepareStatement("INSERT INTO app.class (semester, courseCode, seats) VALUES (?, ?, ?)");
            addClass.setString(1, classEntry.getSemester());
            addClass.setString(2, classEntry.getCourseCode());
            addClass.setInt(3, classEntry.getSeats());  // Assuming seats is an int

            addClass.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        
        }
    }
    
    public static ArrayList<ClassEntry> getAllClasses(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<ClassEntry> classes = new ArrayList<ClassEntry>();
        try
        {
            getClassList = connection.prepareStatement("select semester, coursecode, seats from app.class where semester = ?");
            getClassList.setString(1, semester);
            resultSet = getClassList.executeQuery();
            
            while(resultSet.next())
            {
                classes.add(new ClassEntry(resultSet.getString(1), resultSet.getString(2), Integer.parseInt(resultSet.getString(3))));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return classes;
    }
    
    public static int getClassSeats(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        int count = 0;
        try
        {
            getClassList = connection.prepareStatement("select seats from app.class where semester = ? and coursecode = ?");
            getClassList.setString(1, semester);
            getClassList.setString(2, courseCode);
            resultSet = getClassList.executeQuery();
            
            while(resultSet.next())
                count = resultSet.getInt(1);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return count;
    }
    
        public static void dropClass(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        try
        {
            getClassList = connection.prepareStatement("delete from app.class where semester = ? and coursecode = ?");
            getClassList.setString(1, semester);
            getClassList.setString(2, courseCode);
            getClassList.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
