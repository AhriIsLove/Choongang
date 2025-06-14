package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
