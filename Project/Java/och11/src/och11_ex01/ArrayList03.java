package och11_ex01;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayList03 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("사과");
		list.add("바나나");
		list.add("귤");
		list.add("오렌지");
		list.add("바나나");
		System.out.println("개수 : " + list.size());
		System.out.println("list : " + list);
		
		//Iterator
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			String string = (String) iter.next();
			
			System.out.print(string + "\t");
		}
		System.out.println();
		
		for(int i=0; i<list.size();i++) {
			System.out.print(list.get(i) + "\t");
		}
		System.out.println();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
