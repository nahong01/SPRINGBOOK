package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import springbook.user.domain.User;

public class UserDao {
//public abstract class UserDao {
    protected   Logger      log = Logger.getRootLogger();
    //private SimpleConnectionMaker simpleConnectionMaker;
    private ConnectionMaker connectionMaker;

    public UserDao(){
        //simpleConnectionMaker = new SimpleConnectionMaker();
        connectionMaker = new DConnectionMaker();
    }
    
    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }
    
    public void add(User user) throws ClassNotFoundException, SQLException{
        // 클래스를 분리함
        //Connection c = simpleConnectionMaker.makeNewConnection();
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareCall("INSERT INTO USERS(ID, NAME, PASSWORD) VALUES(?,?,?) ");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        log.debug(ps.toString());
        ps.execute();
    }
    
    public User get(String id) throws ClassNotFoundException, SQLException {
        // 클래스를 분리함
        //Connection c = simpleConnectionMaker.makeNewConnection();
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareCall("SELECT * FROM USERS WHERE ID = ?");
        ps.setString(1, id);
        log.debug(ps.toString());
        
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("ID"));
        user.setName(rs.getString("NAME"));
        user.setPassword(rs.getString("PASSWORD"));
        
        rs.close();
        ps.close();
        c.close();
        
        return user;
    }

}
