package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.chainsys.util.DataBaseConnection2;

public class Employee implements EmployeeDAO{

	
	public boolean registerUser(int userId, String name, String password) throws ClassNotFoundException, SQLException {
	    Connection connection =  DataBaseConnection2.getConnection();

	    String selectQuery = "SELECT name from Employee_details WHERE userId = ?";
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

	    String selectQuery = "SELECT * FROM Employee_details WHERE userId = ? AND password = ?;";
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
    
    
    public void storeLeaveDetails(String leaveType, int duration, Date startDate, Date endDate) throws ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataBaseConnection2.getConnection();
            String insertQuery = "INSERT INTO leavemanagement (leave_type, days, startDate, endDate, userId) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, leaveType);
            preparedStatement.setInt(2, duration);
            if (startDate != null) {
                preparedStatement.setDate(3, new java.sql.Date(startDate.getTime()));
            } else {
                preparedStatement.setNull(3, java.sql.Types.DATE);
            }
            if (endDate != null) {
                preparedStatement.setDate(4, new java.sql.Date(endDate.getTime()));
            } else {
                preparedStatement.setNull(4, java.sql.Types.DATE);
            }
            
            preparedStatement.setInt(5, 1003); 
            
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
}
    

