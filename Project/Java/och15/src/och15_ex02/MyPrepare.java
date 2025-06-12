package och15_ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MyPrepare {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);

		System.out.println("MySQL 부서코드:");
		String dno = sc.nextLine();
		System.out.println("MySQL 부서명:");
		String dname = sc.nextLine();
		System.out.println("MySQL 부서전화번호:");
		String telNum = sc.nextLine();
		System.out.println("MySQL 부서위치:");
		String position = sc.nextLine();

		String sql = String.format("INSERT INTO division(dno, dname, phone, position) VALUES(?,?,?,?)");
		System.out.println("sql:" + sql);

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//HW01(MyPrepare)
		//1.division TBL 입력 (폰 포함)
		//2.prepareStatement 쓰기  
		// 수행 결과
		// 입력할 Mysql 부서코드 ?
		// 55
		// 입력할 Mysql 부서명 ?
		// prepare
		// 입력할 Mysql 폰 ?
		// 010-2222-3333
		// 입력할 Mysql 근무지 ?
		// 신촌
		// Mysql Prepare 입력성공
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
			String url = "jdbc:mysql://127.0.0.1:3306/scottdb";
			conn = DriverManager.getConnection(url, "root", "mysql84");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dno);
			pstmt.setString(2, dname);
			pstmt.setString(3, telNum);
			pstmt.setString(4, position);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) System.out.println("PreparedStatement 입력 성공");
			else System.out.println("PreparedStatement 입력 실패");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("입력 실패");
		} finally {
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		sc.close();
	}

}
