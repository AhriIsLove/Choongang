package och15_ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PostgresInsert01 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("ID : "); String mId = sc.nextLine();
		System.out.println("이름 : "); String mName = sc.nextLine();
		System.out.println("나이 : "); String mAge = sc.nextLine();
		System.out.println("성별 : "); String mGender = sc.nextLine();
		
		String driver = "org.postgresql.Driver" ;
		String url    = "jdbc:postgresql://127.0.0.1:5432/postgres";
		Connection conn = null;
		Statement stmt = null;
		
		String sql = String.format("INSERT INTO member(id, name, age, sex_cd, create_date, update_date) VALUES('%s', '%s', %s, '%s', now(), now())", mId, mName, mAge, mGender);
		
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "postgres", "postgre");
			stmt = conn.createStatement();
			
			int result = stmt.executeUpdate(sql);
			if(result > 0) System.out.println("입력성공");
			else System.out.println("입력실패");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("postgresql 연결 실패");
		} finally {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		
		sc.close();
	}

}
