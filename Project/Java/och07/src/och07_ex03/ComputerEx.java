package och07_ex03;

public class ComputerEx {

	public static void main(String[] args) {
		Computer com = new Computer();
		int[] arr = {1,2,3};
		
		System.out.println("sum1:" + com.sum1(arr));
		
		System.out.println("sum1:" + com.sum1(new int[] {1,2,3,4,5}));
		
		System.out.println("sum2:" + com.sum2(1, 2));
		System.out.println("sum2:" + com.sum2(1, 2, 3));
		System.out.println("sum2:" + com.sum2(1, 2, 3, 4, 5));

	}

}
