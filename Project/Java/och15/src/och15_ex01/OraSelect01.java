package och15_ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OraSelect01 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("부서번호 입력:"); 	String deptno 	= sc.nextLine();
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		//;있으면 실행이 되지 않음
		String sql = String.format("SELECT dname, loc FROM dept WHERE deptno = %s", deptno);
		System.out.println("sql:"+sql);
		
		Connection conn = null;
		Statement stmt = null;//import java.sql.Statement;
		ResultSet result = null;
		
		try {
			Class.forName(driver);//JDBS 드라이버 로드
			conn = DriverManager.getConnection(url, "scott", "tiger");//데이터베이스 연결
			stmt = conn.createStatement();//Statement 생성
			
			result = stmt.executeQuery(sql);//데이터 조회
//			int result = stmt.executeUpdate(sql);//데이터 수정
			
			if(result.next()) {
				String dname = result.getString("dname");
				String loc = result.getString("loc");
				
				System.out.println(String.format("부서코드:%s\t부서명:%s\t위치:%s", deptno, dname, loc));
			}
			else {
				System.out.println("자료 없음");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(result != null) result.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		
		sc.close();

	}

}
