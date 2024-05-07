package com.chainsys.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseConnection2 {

	
	 public static Connection getConnection() throws ClassNotFoundException, SQLException {
         
     	String URL = "jdbc:mysql://localhost:3306/Employee";
    	    String USER = "root";
    	    String PASSWORD = "root";
    	    
     	   
     	Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
         return connection;
     }

}

