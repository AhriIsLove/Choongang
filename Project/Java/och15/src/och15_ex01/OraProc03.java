package och15_ex01;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class OraProc03 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.print("사원번호 급여인상:");
		String empno = sc.nextLine();//SAMPLE : 7369, 7499
	
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
		Connection conn = null;
		// Procedure 호출시
		CallableStatement cstmt = null;
	
		String sql = "{? = call func_sal(?)}";
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, "scott", "tiger");
			cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1, Types.DOUBLE);//return
			cstmt.setString(2, empno);//param
			cstmt.executeQuery();
			
			double sal = cstmt.getDouble(1);
			
			if(sal > 0) {
				System.out.println("수정 성공");
				System.out.println("잘한사원:"+empno);
				System.out.println("급여인상:"+sal);
			}
			else {
				System.out.println("수정 실패");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(cstmt != null) cstmt.close();
			if(conn != null) conn.close();
		}

		sc.close();
	}

}
