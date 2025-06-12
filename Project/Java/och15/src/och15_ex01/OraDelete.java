package och15_ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OraDelete {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 부서코드:"); String deptno = sc.nextLine();
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection conn = DriverManager.getConnection(url, "scott", "tiger");
		Statement stat = conn.createStatement();
		
		// Hw02 
		// 삭제할 부서를 입력 코드를 이용하여 삭제 
		// 삭제할 부서를 입력하세요 ?
		// 53
		// 삭제 성공 ^^
		String sql = String.format("DELETE FROM dept WHERE deptno = %s", deptno);
		System.out.println("sql:" + sql);
		
		try {
			Class.forName(driver);
			
			int result = stat.executeUpdate(sql);
			
			if(result > 0)
			{
				System.out.println(result + "행 삭제 성공");
			}else
			{
				System.out.println("삭제 실패");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stat != null) stat.close();
			if(conn != null) conn.close();
		}
		
		sc.close();
		
	}

}
