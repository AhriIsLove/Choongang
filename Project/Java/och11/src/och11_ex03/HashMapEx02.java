package och11_ex03;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMapEx02 {

	public static void main(String[] args) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		
		hashMap.put("김민재", "010-1234-5678");
		hashMap.put("손흥민", "010-7913-8462");
		hashMap.put("전지현", "010-0000-0000");
		
		//map의 ket는 set으로 구성되어있다.
		Set<String> set = hashMap.keySet();
		for(String str : set) {
			System.out.println(str + "의 값 : " + hashMap.get(str));
		}
		
		System.out.println();
		
		Iterator<String> iter = hashMap.keySet().iterator();
		while (iter.hasNext()) {
			String string = (String) iter.next();
			System.out.println(string + "의 값 : " + hashMap.get(string));
		}

		System.out.println();
		
	}

}
