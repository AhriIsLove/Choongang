package och12_mvc01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDao {
	private static MemberDao instance = null;
	private MemberDao() {
		// TODO Auto-generated constructor stub
	}
	public static MemberDao getInstance() {
		if(instance == null) instance = new MemberDao();
		
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public int check(String p_Id, String p_Password) throws SQLException {
		int result = -2;//DB접근 에러
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT passwd FROM member2 WHERE id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_Id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(p_Password)) {
					result = 1;
				}
				else {
					result = 0;
				}
			}
			else {
				result = -1;
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
	public int insert(Member p_Member) throws SQLException {
		int result = 0;

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO member2(id, passwd, name, address, tel, reg_date) VALUES(?,?,?,?,?,sysdate)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p_Member.getId());
			pstmt.setString(2, p_Member.getPasswd());
			pstmt.setString(3, p_Member.getName());
			pstmt.setString(4, p_Member.getAddress());
			pstmt.setString(5, p_Member.getTel());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return result;
	}
	public int confirm(String p_Id) throws SQLException {
		int result = 0;

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM member2 WHERE id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p_Id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//있다 : 사용불가능
				result = 1;
			}
			else {
				//없다 : 사용가능
				result = 0;
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
	public List<Member> list() throws SQLException{
		List<Member> returnList = new ArrayList<Member>();

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT id, name, address, tel, reg_date FROM member2";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Member m = new Member();
				m.setId(rs.getString(1));
				m.setName(rs.getString(2));
				m.setAddress(rs.getString(3));
				m.setTel(rs.getString(4));
				m.setReg_date(rs.getDate(5));
				
				returnList.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return returnList;
	}
	public Member select(String p_Id) throws SQLException {
		Member r_Member = new Member();

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT id, name, address, tel, reg_date FROM member2 WHERE id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_Id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				r_Member.setId(rs.getString(1));
				r_Member.setName(rs.getString(2));
				r_Member.setAddress(rs.getString(3));
				r_Member.setTel(rs.getString(4));
				r_Member.setReg_date(rs.getDate(5));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return r_Member;
	}
	public int update(Member p_Member) throws SQLException {
		int result = 0;

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		
		String sql = "UPDATE member2 SET passwd = ?, name = ?, address = ?, tel = ? WHERE id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p_Member.getName());
			pstmt.setString(2, p_Member.getPasswd());
			pstmt.setString(3, p_Member.getAddress());
			pstmt.setString(4, p_Member.getTel());
			pstmt.setString(5, p_Member.getId());
			
//			rs = pstmt.executeQuery();
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return result;
	}
	public int delete(String p_Id, String p_Passwd) throws SQLException {
		int result = -1;

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		
		String sql = "DELETE FROM member2 WHERE id = ? AND passwd = ?";
		
//		//선생님 방법
//		result = check(p_Id, p_Passwd);
//		if(result != 1) return result;
//		String sql = "DELETE FROM member2 WHERE id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p_Id);
			pstmt.setString(2, p_Passwd);
			
//			rs = pstmt.executeQuery();
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return result;
	}
}