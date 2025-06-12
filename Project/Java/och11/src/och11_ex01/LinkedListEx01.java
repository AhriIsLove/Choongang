package och11_ex01;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListEx01 {

	public static void main(String[] args) {
		LinkedList<String> linkedList = new LinkedList<String>();
		String[] animal = {"산토끼", "고양이", "진돗개", "치와와", "토끼", "고양이"};
		for(String str : animal) {
			linkedList.add(str);
		}
		
		System.out.println("linkedList : " + linkedList);
		
		System.out.print("Iterator : ");
		Iterator<String> iter = linkedList.iterator();
		while (iter.hasNext()) {
			String string = (String) iter.next();
			System.out.print(string + "\t");
		}
		System.out.println("");

		System.out.println("linkedList : " + linkedList);
		
		//poll : 조회하며 삭제
		System.out.print("linkedList(isEmpty-poll) : ");
		while (!linkedList.isEmpty()) {
			System.out.print(linkedList.poll() + "\t");
		}
		System.out.println("");
		
		System.out.println("linkedList : " + linkedList);
	
	}
}
