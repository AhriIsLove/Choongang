package och02_ex03;

public class Operator2 {

	public static void main(String[] args) {
		int i1 = 10, i2 = 5;
		boolean bResult;
		
		bResult = i1 > i2;
		System.out.printf("%d %c %d = %b\n", i1, '>', i2, bResult);
		bResult = i1 < i2;
		System.out.printf("%d %c %d = %b\n", i1, '<', i2, bResult);
		bResult = i1 >= i2;
		System.out.printf("%d %s %d = %b\n", i1, ">=", i2, bResult);
		bResult = i1 <= i2;
		System.out.printf("%d %s %d = %b\n", i1, "<=", i2, bResult);
		bResult = i1 == i2;
		System.out.printf("%d %s %d = %b\n", i1, "==", i2, bResult);
		bResult = i1 != i2;
		System.out.printf("%d %s %d = %b\n", i1, "!=", i2, bResult);

	}

}
