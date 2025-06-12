package och10_ex02;

public class Throw01 {

	public static void main(String[] args) {
		System.out.println("안녕");
		
		Exception e1 = new Exception("내가 Error 만들었다.");
		
		try {
			System.out.println("e1 1");
			
			int t = 100/0;
			
			throw e1;
		} catch (Exception e) {
			System.out.println("kkk:" + e);
		}

		System.out.println("프로그램 정상 종료");
	}

}
