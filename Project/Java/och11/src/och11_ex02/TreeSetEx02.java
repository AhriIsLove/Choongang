package och11_ex02;

import java.util.TreeSet;

public class TreeSetEx02 {

	public static void main(String[] args) {
		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		treeSet.add(52);
		treeSet.add(24);
		treeSet.add(72);
		treeSet.add(34);
		treeSet.add(52);
		
		System.out.println(treeSet);
		
		int i = 0;
		for(int num : treeSet) {
			System.out.println(++i + " : " + num);
		}
		
	}

}
