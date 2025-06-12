package och02_ex02;

public class EqualityEx {

	public static void main(String[] args) {
		int i1 = 5;
		int i2 = 2+3;
		
		if(i1 == i2)
			System.out.println("i1과 i2는 일치");
		
		if((i1 != i2) == false)
			System.out.println("i1과 i2는 불일치");

	}

}
