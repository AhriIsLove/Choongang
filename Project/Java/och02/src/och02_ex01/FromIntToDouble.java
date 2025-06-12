package och02_ex01;

public class FromIntToDouble {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i1 = 123456780;
		int i2 = 123456780;
		
		double d1 = i2;
		System.out.println("d1:" + d1);//1.2345678E8 = 123456780

		i2 = (int)d1;
		System.out.println("i2:" + i2);
		
		int i3 = i1 - i2;
		System.out.println("i3:" + i3);
	}

}
