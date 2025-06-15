package com.oracle.oBootBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.oracle.oBootBoard.dto.BDto;

@Repository
public class JdbcDao implements BDao {
	// JDBC 사용
	private final DataSource dataSource;
	public JdbcDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	// DB 연결
	private Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}

	@Override
	public ArrayList<BDto> boardList() {
		ArrayList<BDto> bList = new ArrayList<BDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("BDao boardList start...");
		
		String sql = "SELECT * FROM MVC_BOARD ORDER BY BGROUP DESC, BSTEP";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bId = rs.getInt("BID");
				String bName = rs.getString("BNAME");
				String bTitle = rs.getString("BTITLE");
				String bContent = rs.getString("BCONTENT");
				Timestamp bDate = rs.getTimestamp("BDATE");
				int bHit = rs.getInt("BHIT");
				int bGroup = rs.getInt("BGROUP");
				int bStep = rs.getInt("BSTEP");
				int bIndent = rs.getInt("BINDENT");
				BDto bDto = new BDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent);
				bList.add(bDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bList;
	}
	
	@Override
	public BDto contentView(String strId) throws SQLException {
		BDto bDto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//히트(조회수) 증가
		upHit(strId);

		String sql = "SELECT * FROM MVC_BOARD WHERE bId = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bId = rs.getInt("BID");
				String bName = rs.getString("BNAME");
				String bTitle = rs.getString("BTITLE");
				String bContent = rs.getString("BCONTENT");
				Timestamp bDate = rs.getTimestamp("BDATE");
				int bHit = rs.getInt("BHIT");
				int bGroup = rs.getInt("BGROUP");
				int bStep = rs.getInt("BSTEP");
				int bIndent = rs.getInt("BINDENT");
				bDto = new BDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return bDto;
	}
	
	private int upHit(String strId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		int result = 0;

		String sql = "UPDATE MVC_BOARD SET bHit = bHit+1 WHERE bId = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			
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
	
	@Override
	public int modify(String bId, String bName, String bTitle, String bContent) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		int result = 0;

		String sql = "UPDATE MVC_BOARD SET bName = ?, bTitle = ?, bContent = ? WHERE bId = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bId);
			
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

	@Override
	public int delete(String bId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		int result = 0;

		String sql = "DELETE FROM MVC_BOARD WHERE bId = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bId);
			
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
	
	@Override
	public int write(String bName, String bTitle, String bContent) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		int result = 0;

		String sql = "INSERT INTO MVC_BOARD (bName, bTitle, bContent, bid, bGroup, bHit, bStep, bIndent, bDate) VALUES (?,?,?,mvc_board_seq.nextval,mvc_board_seq.currval,0,0,0,sysdate)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			
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
