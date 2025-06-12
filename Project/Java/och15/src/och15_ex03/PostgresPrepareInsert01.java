package och15_ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PostgresPrepareInsert01 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("PostgresPrepareInsert01");
		System.out.println("ID : "); String mId = sc.nextLine();
		System.out.println("이름 : "); String mName = sc.nextLine();
//		System.out.println("나이 : "); int mAge = sc.nextInt();//여기서 숫자 + enter를 입력받아 버림
//		System.out.println("성별[1 or 2] : "); String mGender = sc.nextLine();//여기서 남은 enter를 입력받아 바로 넘어가버림
		System.out.println("나이 : "); String mAge = sc.nextLine();
		System.out.println("성별[1 or 2] : "); String mGender = sc.nextLine();
		
		String driver = "org.postgresql.Driver" ;
		String url    = "jdbc:postgresql://127.0.0.1:5432/postgres";
		Connection conn = null;
		PreparedStatement pstmt = null;
//		String sql = "INSERT INTO member(id, name, age, sex_cd, create_date, update_date) VALUES (?, ?, ?, ?, now(), now())";
		String sql = "INSERT INTO member VALUES (nextval('MEMBER_SEQ'), ?, ?, ?, ?, now(), now())";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "postgres", "postgre");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mId);
			pstmt.setString(2, mName);
			pstmt.setInt(3, Integer.parseInt(mAge));
			pstmt.setString(4, mGender);
			
			int result = pstmt.executeUpdate();
			if(result > 0) System.out.println("입력 성공");
			else System.out.println("입력 성공");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn !=  null) conn.close();
		}
		
		sc.close();

	}

}
