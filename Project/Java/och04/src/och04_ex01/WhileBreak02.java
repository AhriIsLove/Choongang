package och04_ex01;

public class WhileBreak02 {

	public static void main(String[] args) {
		int i=0, j=0;
		while(true)
		{
			i++;
			while(true)
			{
				j++;
				System.out.print("j:" + j + "\t");
				if(j == 5)break;
			}
			j=0;
			System.out.println("\ni:" + i);
			if(i == 10)break;
		}
		System.out.println("끝");
	}

}
