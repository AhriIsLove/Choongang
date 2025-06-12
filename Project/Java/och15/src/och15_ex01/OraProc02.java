package och15_ex01;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class OraProc02 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.print("사원번호 검색:");
		int empno = sc.nextInt();//SAMPLE : 7369

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		Connection conn = null;
		// Procedure 호출시
		CallableStatement cstmt = null;

		// 사번을 입력하면 이름,급여를 반환하는 프로시저
		String sql = "{call emp_Info2(?,?,?)}";// 프로시저(IN, OUT, OUT)

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"scott","tiger");
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, empno);// IN
			cstmt.registerOutParameter(2, Types.VARCHAR);// OUT
			cstmt.registerOutParameter(3, Types.DOUBLE);// OUT

			cstmt.executeQuery();

			String ename = cstmt.getString(2);
			int sal = cstmt.getInt(3);

			System.out.println("사번:" + empno);
			System.out.println("이름:" + ename);
			System.out.println("급여:" + sal);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (cstmt != null)
				cstmt.close();
			if (conn != null)
				conn.close();
		}

		sc.close();

	}

}
