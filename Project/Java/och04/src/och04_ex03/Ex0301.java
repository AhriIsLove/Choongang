package och04_ex03;

public class Ex0301 {

	public static void main(String[] args) {
		int sum = 0;

		// HW05
		// for문 이용 1부터 100, 3의 배수의 합  sum
		
		for(int i=1; i<=100; i++)
		{
			if(i % 3 == 0)
			{
//				System.out.println("i->" + i);
				sum += i;
			}
		}
		
		System.out.println("1~100까지의 3의 배수 합:" + sum);
	}

}
