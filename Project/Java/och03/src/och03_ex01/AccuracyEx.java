package och03_ex01;

public class AccuracyEx {

	public static void main(String[] args) {
		int iApple = 1;
		double dPieceUnit = 0.1;//조각 단위
		int iNumber = 7;
		
		double dResult = iApple - iNumber * dPieceUnit;
		System.out.println("사과 한개에서 ");
		System.out.println("0.7조각을 빼면 ");
		System.out.println(dResult + "조각이 남는다.");
	}

}
