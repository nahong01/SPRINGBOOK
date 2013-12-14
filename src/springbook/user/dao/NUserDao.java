package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao{

    public Connection getConnection() throws ClassNotFoundException,SQLException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "spring", "book");
        return c;
    }

}
