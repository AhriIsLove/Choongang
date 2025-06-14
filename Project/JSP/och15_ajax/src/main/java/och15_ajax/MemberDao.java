package och15_ajax;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDao {
	private static MemberDao instance;
	private MemberDao() {
		// TODO Auto-generated constructor stub
		
	}
	public static MemberDao getInstance() {
		if(instance == null) instance = new MemberDao();
		
		return instance;
	}
	private Connection getConnection() {
		Connection conn = null;
		
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			
			conn = ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public int confirm(String p_Id) throws SQLException {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT id FROM member1 WHERE id = ?";
		
		try {
			pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, p_Id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;//중복 아이디 존재
			}else {
				result = 0;//중복 아이디 없음
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}

		return result;
	}
}
