package com.envision.automation.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDbUtils {
    //Step1 : Make a connection with db
    public Connection getConnection(String dbUrl, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); //register the driver to use for db
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        //step 2: create a statement

        //step5 : fetch values
        return connection;
    }

    public ResultSet getResultSet(Connection conn, String sqlQuery) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute(sqlQuery);
        ResultSet rs = stmt.getResultSet();
        return rs;
    }

    public List<List<String>>fetchEmployeeData(ResultSet rs) throws SQLException {
        List<List<String>> multipleEmployeeData = new ArrayList<List<String>>();
        while (rs.next()) {
            List<String> employeeData = new ArrayList<String>();
            int employeeId = rs.getInt(1);
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            employeeData.add(String.valueOf(employeeId));
            employeeData.add(firstName);
            employeeData.add(lastName);
            employeeData.add(email);
            multipleEmployeeData.add(employeeData);
        }
        return multipleEmployeeData;
    }

    //
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySqlDbUtils sqlDbUtils = new MySqlDbUtils();
        Connection connection =sqlDbUtils.getConnection("jdbc:mysql://localhost:3306/automationDB", "root", "automation123"); //combination of host, port and jdbc keyword
        ResultSet rs =sqlDbUtils.getResultSet(connection,"select employee_id, first_name, last_name, email from employees");
        List<List<String>> multipleEmployeeData =sqlDbUtils.fetchEmployeeData(rs);
        System.out.println(multipleEmployeeData);
    }
}
