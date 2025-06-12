package och04_ex01;

public class For01 {

	public static void main(String[] args) {
		int iSum = 0;
		
		// 10까지의 합
		for(int i=1; i<=10; i++)
		{
			iSum += i;
			System.out.println(i + "까지의 합:" + iSum);
		}
			
		System.out.println("총합:" + iSum);

	}

}
