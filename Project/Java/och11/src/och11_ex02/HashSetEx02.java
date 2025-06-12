package och11_ex02;

import java.util.HashSet;
import java.util.Scanner;

public class HashSetEx02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("첫번째 수 : ");
		int num1 = sc.nextInt();
		
		System.out.print("두번째 수 : ");
		int num2 = sc.nextInt();

		HashSet<Integer> hashSet = new HashSet<Integer>();
		hashSet.add(num1);
		hashSet.add(num2);
		
		while (true) {
			int num = (int)(Math.random()*45) + 1;
			System.out.println("num : " + num);
			hashSet.add(num);
			
			if(hashSet.size() == 6) break;
		}
		System.out.println(hashSet);
		sc.close();
		
	}

}
