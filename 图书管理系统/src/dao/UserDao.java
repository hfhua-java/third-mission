package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import act.User;
import util.DbUtil;


/**
 * 更改用户信息
 */
public class UserDao {
	    

	/**
	 * 修改用户信息
	 */
	public void updateUser(User user,String username, String password) {
		String sql = "update users "
				+ "set user=?,password=? "
				+ "where id=1";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, username);
			pstmt.setString(2, password);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DbUtil.close((com.mysql.jdbc.PreparedStatement) pstmt);
			DbUtil.close(conn);        //必须关闭
		}
	}


	/**
	 * 查询用户信息
	 */
	public List<User> findUserList(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs;
		//创建一个集合对象用来存放查询到的数据
		List<User> userList = new ArrayList<>();
		User user;
		try {
			conn = DbUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select user,password from users");
			while (rs.next()){
				user = new User();
				user.setUser(rs.getString("user"));
				user.setPasswd(rs.getString("password"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DbUtil.close((com.mysql.jdbc.PreparedStatement) stmt);
			DbUtil.close(conn);		//必须关闭
		}
		return userList;
	}

	
}
