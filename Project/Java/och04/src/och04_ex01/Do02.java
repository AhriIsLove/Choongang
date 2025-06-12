package och04_ex01;

public class Do02 {

	public static void main(String[] args) {
		int i=1, j=2;
		System.out.println("DoWhile 구구단");
		do
		{
			do
			{
				System.out.print(j + "*" + i + "=" + i*j + "\t");
				j++;
			}while(j<=9);
			System.out.println();
			j = 2;
			i++;
		}while(i<=9);
	}

}
