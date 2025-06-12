package och02_ex02;

public class ConditionalOpEx2 {

	public static void main(String[] args) {
		int i1 = 20;
		int i2 = 30;
		int iMax;
		//삼항 연산자
//		iMax = i1 > i2 ? i1 : i2;
		if(i1 > i2)
			iMax = i1;
		else
			iMax = i2;
		
		System.out.println("iMax:" + iMax);

	}

}
