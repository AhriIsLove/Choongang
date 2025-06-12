package och07;

import java.util.Scanner;

class Factorial
{
	int result = 1;
	
	void DoFactorial(int cnt)
	{
		if(cnt > 1)
		{
			result *= cnt;
			System.out.print(cnt + " * ");
			DoFactorial(--cnt);
		}
		else
		{
			System.out.println(cnt + " = " + result);
		}
	}
}

public class FactorialEx {

	public static void main(String[] args) {
		int nNum;
		System.out.print("Factorial 구하기(13이상의 숫자는 표현 불가능) : ");
		Scanner sc = new Scanner(System.in);
		nNum = sc.nextInt();
		
		Factorial fac = new Factorial();
		fac.DoFactorial(nNum);
		

	}

}
