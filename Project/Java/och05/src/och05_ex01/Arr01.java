package och05_ex01;

public class Arr01 {

	public static void main(String[] args) {
		int[] a = new int[5];
		a[0] = 7;
		a[1] = 34;
		a[2] = 67;
		a[3] = 6;
		a[4] = 234;
		
		System.out.println("배열의 길이 : " + a.length);

		for(int i=0; i<a.length; i++)
		{
			System.out.printf("a[%d] : %d\n", i, a[i]);
		}

		System.out.println();
		
		int[] b = new int[5];
		b[0] = 67;
		b[1] = 375684;
		b[2] = 8767;
		b[3] = 685;
		b[4] = 23404;
		
		for(int i=0; i<b.length; i++)
		{
			System.out.printf("b[%d] : %d\n", i, b[i]);
		}
		
	}

}
