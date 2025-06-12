package och15_ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MyInsert01 {

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

		String sql = String.format("INSERT INTO division(dno, dname, phone, position) VALUES(%s, '%s', '%s', '%s')", dno, dname, telNum,
				position);
		System.out.println("sql:" + sql);

		Connection conn = null;
		Statement stmt = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
			String url = "jdbc:mysql://127.0.0.1:3306/scottdb";
			conn = DriverManager.getConnection(url, "root", "mysql84");
			stmt = conn.createStatement();
			
			int result = stmt.executeUpdate(sql);
			if(result > 0) System.out.println("MySQL 입력성공");
			else System.out.println("MySQL 입력실패");
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// TODO: handle finally clause
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}

		sc.close();

	}

}
