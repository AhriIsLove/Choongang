package och02_ex01;

public class CheckValueBeforeCast {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i1 = 126;
		int i2 = 258;//-128~127
		
		byte b1 = (byte)i1;
		System.out.println("b1:" + b1);
		
		if(i2<Byte.MIN_VALUE || i2>Byte.MAX_VALUE) {
			System.out.println("byte 타입으로 변환 불가능");
			System.out.println("i2 값 재확인 필요");

			//출력
			byte b2 = (byte)i2;
			System.out.println("b2:" + b2);
		}
		else
		{
			//출력
			byte b2 = (byte)i2;
			System.out.println("b2:" + b2);
		}
		
	}

}