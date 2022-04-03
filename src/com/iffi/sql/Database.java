package com.iffi.sql;

import java.sql.*;

public class Database {

    private static final String PARAMETERS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String USERNAME = "abusch";
    private static final String PASSWORD = "r2b:Vp";
    private static final String URL = "jdbc:mysql://cse.unl.edu/" + USERNAME + PARAMETERS;

    public static Connection connect() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void disconnect(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
