package och11_ex01;

import java.util.ArrayList;

public class ArrayList02 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("수박");
		list.add("사과");
		list.add("바나나");
		list.add("수박");
		list.add("대추");
		list.add("바나나");
		
//		System.out.println("개수 : " + list.size());
		devPrint(list);
		
		list.add(1, "딸기");
		
		devPrint(list);
		
		list.set(4, "복숭아");
		
		devPrint(list);
		
		list.remove(0);
		
		devPrint(list);
		
		System.out.println("3 : " + list.get(3));
		System.out.println("바나나 유무 : " + list.contains("바나나"));
		System.out.println("첫 바나나 : " + list.indexOf("바나나"));
		System.out.println("마지막 바나나 : " + list.lastIndexOf("바나나"));
		for(int i=0;i<list.size();i++) {
			if(list.get(i).equals("바나나")) {
				list.set(i, "방울토마토");
			}
		}
		
		devPrint(list);
		
	}
	
	private static void devPrint(ArrayList<String> pList) {
		System.out.println("개수 : " + pList.size());
		for(String str : pList)
		{
			System.out.print(str + "\t");
		}
		System.out.println("\n---------");
	}

}
