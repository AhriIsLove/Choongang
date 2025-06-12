package och02_ex01;

public class PromotionEx {

	public static void main(String[] args) {
		//byte -> int 형변환
		byte byteValue = 10;
		int iValue = byteValue;
		
		System.out.println("iValue:"+iValue);
		
		//char -> int 형변환
		char charValue = '가';
		iValue = charValue;
		
		System.out.println("charValue:"+charValue);
		System.out.println("iValue:"+iValue);
		
		//int -> long 형변환
		iValue = 500;
		long lValue = iValue;
		
		System.out.println("lValue:"+lValue);
	}

}
