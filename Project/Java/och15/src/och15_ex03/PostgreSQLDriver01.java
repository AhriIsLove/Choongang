package och15_ex03;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQLDriver01 {

	public static void main(String[] args) {
		String driver = "org.postgresql.Driver" ;
		String url    = "jdbc:postgresql://127.0.0.1:5432/postgres";
		
		try
		{
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, "postgres", "postgre");
			if(conn!=null) {
				System.out.println("postgresql 연결 성공");
			}
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("postgresql 연결 실패");
		}
	}

}
