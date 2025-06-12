package och02_ex03;

public class Operator1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i1 = 35;
		int i2 = 10;
		int iResult;
		
		iResult = i1 + i2;
		//%d : decimal, %c : char
		System.out.printf("%d %c %d = %d\n", i1, '+', i2, iResult);
		System.out.println(i1 + " + " + i2 + " = " + iResult);
		
		//에러
//		System.out.println("%d %c %d = %d\n", i1, '+', i2, iResult);

		iResult = i1 - i2;
		System.out.printf("%d %c %d = %d\n", i1, '-', i2, iResult);

		iResult = i1 * i2;
		System.out.printf("%d %c %d = %d\n", i1, '*', i2, iResult);

		iResult = i1 / i2;
		System.out.printf("%d %c %d = %d\n", i1, '/', i2, iResult);

		iResult = i1 % i2;
		System.out.printf("%d %c %d = %d\n", i1, '%', i2, iResult);
		
	}

}
