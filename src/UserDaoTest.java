import java.sql.SQLException;

import org.apache.log4j.PropertyConfigurator;

import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserDaoTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        PropertyConfigurator.configure("c:/PROJECT/workspace2/SPRINGBOOK/logConfig/log4j.lcf");

        //ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao dao = new DaoFactory().userDao();

        User user = new User();
        user.setId("TESTID");
        user.setName("TESTNAME");
        user.setPassword("testpass");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공!!");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getId());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공!!!");
    }

}
