package och05_ex01;

public class MultiArr01 {

	public static void main(String[] args) {
		 int[][] a = new int[2][3];
		 a[0][0] = 1;
		 a[0][1] = 2;
		 a[0][2] = 3;
		 a[1][0] = 11;
		 a[1][1] = 12;
		 a[1][2] = 13;
		 
		 System.out.println("FOR");
		 for(int i=0; i<a.length; i++)
		 {
			 for(int j=0; j<a[i].length;j++)
			 {
				 System.out.print(a[i][j] + "\t");
			 }
			 System.out.println();
		 }
		 System.out.println();
		 

		 System.out.println("향상 FOR");
		 for(int[] k : a)
		 {
			 for(int kk : k)
			 {
				 System.out.print(kk + "\t");
			 }
			 System.out.println();
		 }

	}

}
