package och15_ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OraUpdate {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.print("수정할 부서코드:"); String deptno = sc.nextLine();
		System.out.print("부서명:"); String dname = sc.nextLine();
		System.out.print("근무지:"); String loc = sc.nextLine();
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection conn = DriverManager.getConnection(url, "scott", "tiger");
		Statement stat = conn.createStatement();
		
		// Hw01
		// Update 가능하도록 완성
		
		String sql = String.format("UPDATE dept SET dname = '%s', loc = '%s' WHERE deptno = '%s'", dname, loc, deptno);
		System.out.println("sql:" + sql);
		
		try {
			Class.forName(driver);
			
			int result = stat.executeUpdate(sql);
			
			if(result > 0)
			{
				System.out.println(result + "행 변경됨");
			}
			else
			{
				System.out.println("실패");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stat!= null) stat.close();
			if(conn!= null) conn.close();
		}

		sc.close();
	}

}
