package och11_ex02;

import java.util.TreeSet;

public class TreeSetEx01 {

	public static void main(String[] args) {
		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		
		while (treeSet.size() < 6) {
			int num = (int)(Math.random()*45) + 1;
			System.out.println(num);
			treeSet.add(num);
		}

		System.out.println(treeSet);
		
		

	}

}
