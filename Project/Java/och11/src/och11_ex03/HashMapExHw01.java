package och11_ex03;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapExHw01 {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("신용권", 85);
		map.put("홍길동", 90);
		map.put("신용균", 80);
		map.put("홍길동", 95);
		System.out.println("총 Entry 수 : " + map.size());
		
		System.out.println("전체 : " + map);
		System.out.println("신용균 : " + map.get("신용균"));

		System.out.println();
		
		//--- 객체를 하나씩 처리 keySet ->iterator   --- 
		//신용균 : 80
		//홍길동 : 95
		//신용권 : 85
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String string = (String) iter.next();
			System.out.println(string + " : " + map.get(string));
			
		}
		
	}

}
