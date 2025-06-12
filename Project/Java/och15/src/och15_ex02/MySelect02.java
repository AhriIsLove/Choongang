package och15_ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySelect02 {

	public static void main(String[] args) throws SQLException {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/scottdb";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		System.out.println("MySQL 교수명단");
		System.out.println("교수번호\t이름\t직위\t급여\t학과코드");
		System.out.println("===========================================");

		// Hw02
		// Table professor 이용하여 교수번호\t이름\t직위\t\t급여\t학과코드 출력 
//		       모든 Row 전체 조회 
		//MySQL   교수명단
		//교수번호	이름	직위	급여	학과코드
		//===========================================
		//9901	김도훈	교수	500	101
		//9902	이재우	조교수	320	201
		//9903	성연희	조교수	360	101
		//9904	염일웅	전임강사	240	102
		//9905	권혁일	교수	450	102
		//9906	이만식	부교수	420	101
		//9907	전은지	전임강사	210	101
		//9908	남은혁	부교수	400	202
		
		String sql = "SELECT profno, name, position, sal, deptno FROM professor";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "mysql84");
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while (resultSet.next()) {
				String profno = resultSet.getString("profno");
				String name = resultSet.getString("name");
				String position = resultSet.getString("position");
				String sal = resultSet.getString("sal");
				String deptno = resultSet.getString("deptno");
				
				System.out.println(profno + "\t" + name + "\t" + position + "\t" + sal + "\t" + deptno);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		
	}

}
