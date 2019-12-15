package dao;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import domain.User;
import utils.DataSourceUtils;

public class UserDao {
	// �����û�
	public void addUser(User user) throws SQLException {
		String sql = "insert into user(username,password,email,activecode) values(?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user.getUsername(), user.getPassword(),
				user.getEmail(),user.getActiveCode());
		if (row == 0) {
			throw new RuntimeException();
		}
	}

	// ���ݼ���������û�
	public User findUserByActiveCode(String activeCode) throws SQLException {
		String sql = "select * from user where activecode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), activeCode);

	}

	// �����Ñ�
	public void activeUser(String activeCode) throws SQLException {
		String sql = "update user set state=? where activecode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, 1, activeCode);
	}
	
	//�����û�������������û�
	public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
		String sql="select * from user where username=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class),username,password);
	}
}
