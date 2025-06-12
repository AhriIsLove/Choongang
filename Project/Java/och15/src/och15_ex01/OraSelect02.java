package och15_ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class OraSelect02 {

	public static void main(String[] args) throws SQLException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		String sql = "SELECT * FROM emp";
		
		Connection conn = null;
		Statement stmt = null;//import java.sql.Statement;
		ResultSet result = null;
		
		System.out.println("\t\t\t사원명단");
		System.out.println("사원코드\t사원명\t업무\t\t급여\t일자\t\t");
		System.out.println("---------------------------------------------------------------------------------");
		
		try {
			Class.forName(driver);//JDBS 드라이버 로드
			conn = DriverManager.getConnection(url, "scott", "tiger");//데이터베이스 연결
			stmt = conn.createStatement();//Statement 생성
			
			result = stmt.executeQuery(sql);//데이터 조회
			
			if(result.next()) {
//				7369	SMITH	CLERK		7902	80/12/17	800				20
//				7499	ALLEN	SALESMAN	7698	81/02/20	1600	300		30
//				7521	WARD	SALESMAN	7698	81/02/22	1250	500		30
//				7566	JONES	MANAGER		7839	81/04/02	2975			20
//				7654	MARTIN	SALESMAN	7698	81/09/28	1250	1400	30
//				7698	BLAKE	MANAGER		7839	81/05/01	2850			30
//				7782	CLARK	MANAGER		7839	81/06/09	2450			10
//				7788	SCOTT	ANALYST		7566	87/07/13	3000			20
//				7839	KING	PRESIDENT			81/11/17	5000			10
//				7844	TURNER	SALESMAN	7698	81/09/08	1500	0		30
//				7876	ADAMS	CLERK		7788	87/07/13	1100			20
//				7900	JAMES	CLERK		7698	81/12/03	950				30
//				7902	FORD	ANALYST		7566	81/12/03	3000			20
//				7934	MILLER	CLERK		7782	82/01/23	1300			10
				do {
					int empNo = result.getInt(1);//1부터 시작..
					String empName = result.getString(2);
					String empJob = result.getString(3);
					int empSal = result.getInt("SAL");
					Date empHireDate = result.getDate("HIREDATE");
					
					if(empJob.length() > 7)
						System.out.printf("%d\t%s\t%s\t%d\t%TF\n",empNo, empName, empJob, empSal, empHireDate);
					else
						System.out.printf("%d\t%s\t%s\t\t%d\t%TF\n",empNo, empName, empJob, empSal, empHireDate);
					
				} while(result.next());
				
				
				
			}
			else {
				System.out.println("자료 없음");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(result != null) result.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
	}

}
