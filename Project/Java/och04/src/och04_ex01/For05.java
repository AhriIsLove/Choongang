package och04_ex01;

public class For05 {

	public static void main(String[] args) {
		for(int i=0; i<=4; i++)
		{
			for(int j=0; j<=i; j++)
			{
				System.out.print("*");
			}
			System.out.println();
		}

		System.out.println();
		
		for(int i=4; i>=0; i--)
		{
			for(int j=0; j<=i; j++)
			{
				System.out.print("*");
			}
			System.out.println();
		}
		
	}

}
