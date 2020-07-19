package edu.scu.xyl.utils;

import java.sql.*;

public class MySQLUtils {

    private static final String USER_NAME = "root";

    private static final String PASSWORD = "root";

    private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3306/log_analysis?serverTimezone=GMT";



    // get database connection
    public static Connection getConnection() {

        Connection connection = null;
        try {
            Class.forName(DRIVERCLASS);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }


    public static void release(Connection connection, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
