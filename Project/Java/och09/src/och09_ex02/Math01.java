package och09_ex02;

public class Math01 {

	public static void main(String[] args) {
		int[] nArr_Rotto = new int[6];
		for(int i=0; i<nArr_Rotto.length; i++)
		{
			int num = (int)(Math.random() * 45) + 1;
			nArr_Rotto[i] = num;
		}
		
		for(int i=0; i<nArr_Rotto.length; i++)
		{
			System.out.println(i+1 + ":" + nArr_Rotto[i]);
		}

	}

}
