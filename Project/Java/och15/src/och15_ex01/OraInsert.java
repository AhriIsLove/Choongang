package och15_ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OraInsert {

	public static void main(String[] args) throws SQLException/*finally 에러 예외처리*/ {
		Scanner sc = new Scanner(System.in);
		System.out.println("부서번호 입력:"); 	String deptno 	= sc.nextLine();
		System.out.println("부서명 입력:"); 	String dname 	= sc.nextLine();
		System.out.println("부서위치 입력:"); 	String loc 		= sc.nextLine();
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		//;있으면 실행이 되지 않음
		String sql = String.format("INSERT INTO dept VALUES (%s, '%s', '%s')", deptno, dname, loc);
		System.out.println("sql:"+sql);
		
		Connection conn = null;
		Statement stmt = null;//import java.sql.Statement;
		
		try {
			Class.forName(driver);//JDBS 드라이버 로드
			conn = DriverManager.getConnection(url, "scott", "tiger");//데이터베이스 연결
			stmt = conn.createStatement();//Statement 생성
			
			//실행으로 변경된 로우 수
//			int result = stmt.executeQuery(sql);//데이터 조회
			int result = stmt.executeUpdate(sql);//데이터 수정
			if(result > 0) System.out.println("입력 성공");
			else System.out.println("입력 실패");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		
		sc.close();
	}

}
