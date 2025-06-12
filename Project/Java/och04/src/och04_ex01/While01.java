package och04_ex01;

public class While01 {

	public static void main(String[] args) {
		int iSum = 0, i = 1;
		
		// 10까지의 합
		while (i <= 10) {
			iSum += i;
			System.out.println(i + "까지의 합:" + iSum);
			i++;
		}
		System.out.println("총합:" + iSum);
	}

}
