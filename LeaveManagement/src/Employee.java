


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.dao.EmployeeDAO;
import com.chainsys.util.DataBaseConnection2;

public class Employee implements EmployeeDAO{

	
	public boolean registerUser(int userId, String name, String password) throws ClassNotFoundException, SQLException {
	    Connection connection =  DataBaseConnection2.getConnection();

	    String selectQuery = "SELECT * FROM Employee_details WHERE userId = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	    preparedStatement.setInt(1, userId);
	    ResultSet resultSet = preparedStatement.executeQuery();

	    if (resultSet.next()) {
	        System.out.println("User already exists with this User ID.");
	        preparedStatement.close();
	        connection.close();
	        return false;
	    }
	
	    System.out.println("User registered successfully.");
	    return true;
	}
		

	public  boolean loginUser(int userId, String password) throws ClassNotFoundException, SQLException {
	    Connection connection =  DataBaseConnection2.getConnection();

	    String selectQuery = "SELECT * FROM Employee_details WHERE userId = ? and password = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	    preparedStatement.setInt(1, userId);
	    preparedStatement.setString(2, password);

	    ResultSet resultSet = preparedStatement.executeQuery();

	    if (resultSet.next()) {
	        return true;
	    } else {
	        System.out.println("Invalid User ID or password. Please try again.");
	        return false;
	    }
	}

    public void insertEmployee(int userId,String name, String password) throws ClassNotFoundException, SQLException {
        Connection connection = DataBaseConnection2.getConnection();
       
        String insertQuery = "INSERT INTO Employee_details (userId, name, password) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

        preparedStatement.setInt(1, userId);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, password);

        int rows = preparedStatement.executeUpdate();
        System.out.println(rows + " rows inserted");
        preparedStatement.close();
        connection.close();
    } 

    public  void read() throws ClassNotFoundException, SQLException {
        Connection connection = DataBaseConnection2.getConnection();
        String selectQuery = "SELECT userId, name FROM Employee_details";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Employee Records:");
        while (resultSet.next()) {
            System.out.println("User Id: " + resultSet.getInt("userId") + ", Name: " + resultSet.getString("name"));
        }
    }
}
