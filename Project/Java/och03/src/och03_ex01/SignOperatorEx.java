package och03_ex01;

public class SignOperatorEx {

	public static void main(String[] args) {
		int iX = -100;
		int iResult1 = +iX;
		
		System.out.println("iResult1 초기값 : " + iResult1);

		int iResult2 = 0;
		
		System.out.println("iResult2 초기값 : " + iResult2);
		
		iResult2 = -iX;

		System.out.println("iResult1 초기값 : " + iResult1);
		System.out.println("iResult2 초기값 : " + iResult2);
	}

}
