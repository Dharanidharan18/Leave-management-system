package com.chainsys.dao;

import java.sql.SQLException;
import java.util.Date;

public interface EmployeeDAO {

	boolean registerUser(int userId, String name, String password) throws ClassNotFoundException, SQLException ;
	boolean loginUser(int userId, String password) throws ClassNotFoundException, SQLException ;
    void insertEmployee(int userId,String name, String password) throws ClassNotFoundException, SQLException;
    void read() throws ClassNotFoundException, SQLException;
    void storeLeaveDetails(String leaveType, int duration, Date startDate, Date endDate, int userId) throws ClassNotFoundException;
}