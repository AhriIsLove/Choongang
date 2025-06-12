package och02_ex02;

public class ConditionalOrEx {

	public static void main(String[] args) {
		int i1 = -1, i2 = 0;
		
		if(++i1 > 0 || ++i2 > 0)
		{
			System.out.println("i1이 0보다 크거나 i2가 0보다 큽니다.");
		}
		
		System.out.println("i1:" + i1);
		System.out.println("i2:" + i2);

	}

}
