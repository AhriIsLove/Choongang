package och10_ex01;

public class Exception07 {

	static void callDriver() throws ClassNotFoundException {
		System.out.println("callDriver 수행중...");
		//시스테 드라이버를 찾는다.(없을 수 있으니 예외처리 필수)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("callDriver 완료");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("callDriver 호출전");
		
		try {
			callDriver();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("클래스를 찾을 수 없습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			System.out.println("시스템 종료");
		}

	}

}
