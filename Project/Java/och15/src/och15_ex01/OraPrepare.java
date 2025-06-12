package och15_ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OraPrepare {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.print("부서코드:"); String deptno = sc.nextLine();
		System.out.print("부서명:"); String dname = sc.nextLine();
		System.out.print("근무지:"); String loc = sc.nextLine();

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		Connection conn = null;
		PreparedStatement pstmt = null;//준비된 STMT (파라미터 나중에 받음)
		
//		String sql    = "INSERT INTO dept VALUES("+ deptno+",'"+dname+"','"+loc+"')"; 위와 같은 의미
		String sql = "INSERT INTO dept VALUES(?, ?, ?)";//파라미터만 가능
		System.out.println("sql:" + sql);
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "scott", "tiger");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptno);
			pstmt.setString(2, dname);
			pstmt.setString(3, loc);
			
//			ResultSet result = pstmt.executeQuery();
			int result = pstmt.executeUpdate();
			if(result > 0) System.out.println("입력성공");
			else System.out.println("입력실패");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		sc.close();

	}

}
