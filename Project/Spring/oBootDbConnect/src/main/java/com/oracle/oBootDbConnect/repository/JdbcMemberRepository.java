package com.oracle.oBootDbConnect.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.oracle.oBootDbConnect.domain.Member7;

//@Repository
public class JdbcMemberRepository implements MemberRepository {
	// JDBC 사용
	private final DataSource dataSource;
	@Autowired
	public JdbcMemberRepository(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.dataSource = dataSource;// application.properties의 DataSource정보를 가져온다.
	}
	
	private Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}
	
	@Override
	public Member7 save(Member7 member7) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		Member7 returnMember7 = null;
		
		try {
			conn = getConnection();
			
			String sql = "INSERT INTO member7(id, name) VALUES(MEMBER7_SEQ.nextval, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member7.getName());
			
//			rs = pstmt.executeQuery();
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				returnMember7 = member7;
				returnMember7.setId(1L);//임시 ID 부여
			}
			
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
		
		return returnMember7;
	}
	
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null) close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void close(Connection conn) throws SQLException {
		//리소스, 메모리 해제
		DataSourceUtils.releaseConnection(conn, dataSource);
	}

	@Override
	public List<Member7> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		List<Member7> members = new ArrayList<>();
		
		try {
			conn = getConnection();
			
			String sql = "SELECT * FROM member7 ORDER BY id";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
//			result = pstmt.executeUpdate();
			
			while (rs.next()) {
				Member7 member7 = new Member7();
				member7.setId(rs.getLong("id"));
				member7.setName(rs.getString("name"));
				
				members.add(member7);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
		
		return members;
	}

}
