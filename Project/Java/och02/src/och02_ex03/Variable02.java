package och02_ex03;

public class Variable02 {

	public static void main(String[] args) {
		int i1 = 90;
		int i2 = 90;
		int i3 = 91;
		int iSum;
		double dAvr1;
		double dAvr2;
		double dAvr3;
		double dAvr4;
		
		iSum = i1 + i2 + i3;
		
		dAvr1 = iSum / 3;
		dAvr2 = iSum / (float)3;
		dAvr3 = (float)(iSum / 3);
		dAvr4 = (float)iSum / 3;

		System.out.printf("총점 = %d\n", iSum);
		System.out.printf("평균 = %.1f\n", dAvr1);
		System.out.printf("평균 = %f\n", dAvr2);
		System.out.printf("평균 = %.1f\n", dAvr3);
		System.out.printf("평균 = %.1f\n", dAvr4);
	}

}


