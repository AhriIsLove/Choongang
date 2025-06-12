package och15_ex02;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlDriver01 {

	public static void main(String[] args) {
		String driver = "com.mysql.cj.jdbc.Driver" ;
		String url    = "jdbc:mysql://127.0.0.1:3306/scottdb";

		try {
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, "root", "mysql84");
			if(conn != null) {
				System.out.println("MySQl 연결 성공");
			}
			else {
				System.out.println("MySQl 연결 실패"); 
			}
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		
	}

}
