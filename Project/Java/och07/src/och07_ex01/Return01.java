package och07_ex01;

class Divide{
	void Divide_NoReturn(int x, int y) {
		if(y == 0) {
			System.out.println("0으로는 나눌 수 없음");
			return;
		}
		System.out.printf("%d / %d = %d\n", x, y, x/y);
	}
	int Divide_Return(int x, int y) {
		if(y == 0) {
			System.out.println("0으로는 나눌 수 없음");
			return y;
		}
		System.out.printf("%d / %d = %d\n", x, y, x/y);
		return x/y;
	}
}

public class Return01 {

	public static void main(String[] args) {
		int resultDivide1, resultDivide2, resultDivide3;
		
		Divide div = new Divide();
		div.Divide_NoReturn(13, 3);

		resultDivide1 = div.Divide_Return(13, 3);
		System.out.println("resultDivide1:" + resultDivide1);
		resultDivide2 = div.Divide_Return(23, 3);
		System.out.println("resultDivide2:" + resultDivide2);
		resultDivide3 = div.Divide_Return(33, 3);
		System.out.println("resultDivide3:" + resultDivide3);
		
		int total = resultDivide1 + resultDivide2 + resultDivide3;
		System.out.println("total:" + total);

	}

}
