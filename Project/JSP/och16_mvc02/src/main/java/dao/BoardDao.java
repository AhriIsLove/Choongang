package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDao {
	private static BoardDao instance;

	private BoardDao() {
		// TODO Auto-generated constructor stub
	}

	public static BoardDao getInstance() {
		if (instance == null)
			instance = new BoardDao();

		return instance;
	}

	// DB 연결
	public Connection getConnection() {
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

	// get : 게시판의 글 수
	public int getTotalCnt() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		int tot = 0;

		String sql = "SELECT COUNT(*) FROM board";

		conn = getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				tot = rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}

		return tot;
	}
	
	// get : 게시글 목록 정보
	public List<Board> boardList(int startRow, int endRow) throws SQLException {
		// TODO Auto-generated method stub
		List<Board> r_listBoard = new ArrayList<Board>();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

//		//그냥 가져다 붙혀넣어도 되긴 합니다
//		String sql = "SELECT * \r\n"
//					+ "FROM \r\n"
//					+ "    (SELECT rownum AS rn, a.*\r\n"
//					+ "    FROM \r\n"
//					+ "        (SELECT * \r\n"
//					+ "        FROM board \r\n"
//					+ "        ORDER BY ref DESC, re_step ASC\r\n"
//					+ "        ) a\r\n"
//					+ "    )\r\n"
//					+ "WHERE rn BETWEEN ? AND ?\r\n";
		
		String sql = "SELECT * "
					+ "FROM "
					+ "		(SELECT rownum AS rn, a.* "
					+ "		FROM "
					+ "			(SELECT * "
					+ "			FROM board "
					+ "			ORDER BY ref DESC, re_step ASC"
					+ "			) a"
					+ "		)"
					+ "WHERE rn BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
//				board.setNum(rs.getInt("rn"));//1
				board.setNum(rs.getInt("num"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setEmail(rs.getString("email"));
				board.setReadcount(rs.getInt("readcount"));
				board.setPasswd(rs.getString("passwd"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(rs.getInt("re_level"));
				board.setIp(rs.getString("ip"));
				board.setReg_date(rs.getDate("reg_date"));
				
				r_listBoard.add(board);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return r_listBoard;
	}

	// get : 게시글 정보
	public Board select(int num) throws SQLException {
		// TODO Auto-generated method stub
		Board r_Board = new Board();

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM board WHERE num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				r_Board.setNum(rs.getInt("num"));
				r_Board.setWriter(rs.getString("writer"));
				r_Board.setSubject(rs.getString("subject"));
				r_Board.setContent(rs.getString("content"));
				r_Board.setEmail(rs.getString("email"));
				r_Board.setReadcount(rs.getInt("readcount"));
				r_Board.setPasswd(rs.getString("passwd"));
				r_Board.setRef(rs.getInt("ref"));
				r_Board.setRe_step(rs.getInt("re_step"));
				r_Board.setRe_level(rs.getInt("re_level"));
				r_Board.setIp(rs.getString("ip"));
				r_Board.setReg_date(rs.getDate("reg_date"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return r_Board;
	}

	// set : 게시글의 조회수 증가
	public void readCount(int num) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
//		ResultSet rs = null;

//		String sql = "UPDATE board SET readcount = (\r\n"
//				+ "    SELECT readcount+1\r\n"
//				+ "    FROM board\r\n"
//				+ "    WHERE num = ?)\r\n"
//				+ "WHERE num = ?\r\n";

		String sql = "UPDATE board SET readcount = readcount + 1 WHERE num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
//			pstmt.setInt(2, num);
			
			int result = pstmt.executeUpdate();
			//결과를 쓸려면 쓰고...
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}

	// set : 게시글 수정
	public int update(Board board) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		int r_Result = 0;

		String sql = "UPDATE board SET writer = ?, subject = ?, content = ?, email = ?, passwd = ? WHERE num = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getEmail());
			pstmt.setString(5, board.getPasswd());
//			pstmt.setString(6, board.getIp());
			pstmt.setInt(6, board.getNum());

			r_Result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			if(rs != null) rs.close();
			if (pstmt != null)pstmt.close();
			if (conn != null)conn.close();
		}

		return r_Result;
	}

	// set : 게시글 입력
	public int insert(Board board) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		int result = 0;

		String sql = "INSERT INTO board(num, writer, subject, content, email, readcount, passwd, ref, re_step, re_level, ip, reg_date) "
				+ " VALUES ((SELECT MAX(num)+1 FROM board), ?, ?, ?, ?, ?, ?, (SELECT MAX(num)+1 FROM board), ?, ?, ?, sysdate)";
		
		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "");
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getEmail());
			pstmt.setInt(5, board.getReadcount());
			pstmt.setString(6, board.getPasswd());
//			pstmt.setString(8, "MAX(num)+1");
			pstmt.setInt(7, board.getRe_step());
			pstmt.setInt(8, board.getRe_level());
			pstmt.setString(9, board.getIp());
			
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
