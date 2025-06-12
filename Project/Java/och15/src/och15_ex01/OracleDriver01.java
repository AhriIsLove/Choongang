package och15_ex01;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleDriver01 {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		//								DB IP:port:SID
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		System.out.println("start1");
		try {
			// Oracel Driver Memory Up
			Class.forName(driver);
			System.out.println("Start2");
			Connection conn = DriverManager.getConnection(url,"scott","tiger");
			System.out.println("Start3");
			if (conn != null) {
				System.out.println("Success 연결 성공");
			} else {
				System.out.println("Fail");
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
			System.out.println("Error");
		}
	}

}
