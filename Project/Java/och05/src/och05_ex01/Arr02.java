package och05_ex01;

public class Arr02 {

	public static void main(String[] args) {
		int[] a = new int[] {1,2,3,4,5};
		int[] a2 = {6,7,8,9,10};
			 
		System.out.println("--- 일반형 for문 ---");
		for(int i=0; i<a.length; i++)
		{
			System.out.printf("a[%d] : %d\n", i, a[i]);
		}

		System.out.println("--- 향상형 for문 ---");
		for(int kk : a2)
		{
			System.out.println("kk : " + kk);
		}

	}

}
