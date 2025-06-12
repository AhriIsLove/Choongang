package och05_ex01;

public class ArrCopy01 {

	public static void main(String[] args) {
		 int[] a = {1,2,3,4,5,6,7};
		 int[] b = new int[10];

		 System.out.println(b);//해시코드값 16진수
		 System.out.println(b.hashCode());//라는데 같은값이 안나오는거 같은데 ㅠ
		 
		 System.out.println("arraycopy:a,0,b,0,a.length");
		 System.arraycopy(a, 0, b, 0, a.length);
		 pr(b);
		 
		 cls(b);
		 pr(b);
		 
		 System.out.println("arraycopy:a,0,b,2,a.length");
		 System.arraycopy(a, 0, b, 2, a.length);
		 pr(b);
		 
		 cls(b);
		 pr(b);
		 
		 System.out.println("arraycopy:a,1,b,0,a.length-1");
		 System.arraycopy(a, 1, b, 0, a.length-1);
		 pr(b);
		 
		 cls(b);
		 pr(b);
		 
		 System.out.println("arraycopy:a,1,b,3,a.length-1");
		 System.arraycopy(a, 1, b, 3, a.length-1);
		 pr(b);
		 
	}
	
	//main에서 호출하는 함수는 같은 static
	
	//배열 초기화
	private static void cls(int[] p_nArr)
	{
		for(int i=0; i<p_nArr.length;i++)
		{
			p_nArr[i] = 0;
		}
	}
	
	//배열 프린트
	private static void pr(int[] p_nArr)
	{
		for(int i=0; i<p_nArr.length;i++)
		{
			System.out.print(p_nArr[i] + "\t");
		}
		System.out.println();
	}

}
