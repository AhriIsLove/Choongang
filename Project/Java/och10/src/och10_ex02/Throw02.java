package och10_ex02;

public class Throw02 {

	public static void main(String[] args) {
		int num = 100, result = 0;
		for(int i=0; i< 10; i++) {
			try {
				int ran = (int)(Math.random()*10);
				result = num / ran;
				System.out.println("나눗셈 결과 : " + result);	
			} catch (Exception e) {
				// TODO: handle exception
			}


		}

	}

}
