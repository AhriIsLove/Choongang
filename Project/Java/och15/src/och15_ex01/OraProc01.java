package och15_ex01;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class OraProc01 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.print("추가할 부서코드:"); String deptno = sc.nextLine();
		System.out.print("부서명:"); String dname = sc.nextLine();
		System.out.print("근무지:"); String loc = sc.nextLine();

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		Connection conn = null;
		// Procedure 호출시
		CallableStatement cstmt = null;
		
		// Procedure Call할 때의 Format
		String sql = "{call dept_INSERT(?,?,?)}";
		
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "scott", "tiger");
			
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, deptno);
			cstmt.setString(2, dname);
			cstmt.setString(3, loc);
			
			int result = cstmt.executeUpdate();
			if(result > 0) System.out.println("입력성공");
			else System.out.println("입력실패");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(cstmt != null) cstmt.close();
			if(conn != null) conn.close();
		}
		
		sc.close();
		
	}

}
