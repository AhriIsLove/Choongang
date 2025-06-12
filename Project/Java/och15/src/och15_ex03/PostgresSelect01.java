package och15_ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PostgresSelect01 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("조회할 Member의 SEQ입력 : ");
		String mSeq = sc.nextLine();
		
		String driver = "org.postgresql.Driver" ;
		String url    = "jdbc:postgresql://127.0.0.1:5432/postgres";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		String sql = String.format("SELECT seq, id, name, age, sex_cd, update_date FROM member WHERE seq = '%s'", mSeq);
		
		System.out.println("SEQ\tID\tNAME\tAGE\tSEX_CD\tUPDATE_DATE");
		System.out.println("========================================================================");
		
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "postgres", "postgre");
			stmt = conn.createStatement();
			
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println(
						resultSet.getString("seq") 
						+ "\t" + resultSet.getString("id") 
						+ "\t" + resultSet.getString("name")
						+ "\t" +resultSet.getString("age") 
						+ "\t" + resultSet.getString("sex_cd") 
						+ "\t" + resultSet.getString("update_date"));
			}
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
