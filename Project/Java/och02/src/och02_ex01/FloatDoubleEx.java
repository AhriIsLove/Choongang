package och02_ex01;

public class FloatDoubleEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double d1 = 3.14;
		float f1 = 3.14F;	//defalut로 double처리됨
		double d2 = 0.1234567890123456789;//소수점 17자리
		float f2 = 0.1234567890123456789F;//소수점 8자리

		int i3 = 3000000;
		double d3 = 3e6;//3 x 10의6제곱
		double d4 = 3e31;//3 x 10의6제곱

		System.out.println("d1:" + d1);
		System.out.println("f1:" + f1);
		System.out.println("d2:" + d2);
		System.out.println("f2:" + f2);
		
		System.out.println("i3:" + i3);
		System.out.println("d3:" + d3);
		System.out.println("d4:" + d4);
		
		
		//도건
		//double d3 = 3.14;
		
		//byte b2 = byte(d2);
		
		System.out.println();
	}

}
