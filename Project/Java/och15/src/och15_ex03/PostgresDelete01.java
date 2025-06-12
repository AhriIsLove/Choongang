package och15_ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PostgresDelete01 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 Member의 SEQ입력 : ");
		String mSeq = sc.nextLine();
		
		String driver = "org.postgresql.Driver" ;
		String url    = "jdbc:postgresql://127.0.0.1:5432/postgres";
		
		Connection conn = null;
		Statement stmt = null;
		
		//Hw01
		//받은 Seq 이용하여 해당 member Row 삭제
		//Postgres 삭제할 Seq를 입력하세요 ?
		//7
		//Postgres 삭제 성공  ^ ^
		
		String sql = String.format("DELETE FROM member WHERE seq = '%s'", mSeq);
		
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "postgres", "postgre");
			stmt = conn.createStatement();
			
			int result = stmt.executeUpdate(sql);
			if(result > 0) System.out.println("삭제 성공");
			else System.out.println("삭제 실패");
			
			
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
