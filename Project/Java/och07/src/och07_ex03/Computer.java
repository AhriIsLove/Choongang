package och07_ex03;

public class Computer {
	int sum1(int[] values)
	{
		int sum = 0;
		
		for(int i=0; i<values.length; i++)
			sum += values[i];
		
		return sum;
	}
	//함수 내 한번만 사용 가능
	int sum2(int ...values)//받은 파라미터들로 배열 생성
	{
		int sum = 0;
		
		System.out.println("values:"+values);
		for(int i=0; i<values.length; i++)
			sum += values[i];
		
		return sum;
	}
}
