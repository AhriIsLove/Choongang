package och11_ex02;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetEx01 {

	public static void main(String[] args) {
		//순서 X, 중복 X
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add("구렁2");
		hashSet.add("고양이");
		hashSet.add("야옹이");
		hashSet.add("구렁2");
		hashSet.add("진도개");
		hashSet.add("멍멍이");
		System.out.println(hashSet);
		
		for(String str : hashSet) {
			System.out.print(str + "\t");
		}
		System.out.println();
		System.out.println(hashSet);

		Iterator<String> iter = hashSet.iterator();
		while (iter.hasNext()) {
			String str = (String) iter.next();
			System.out.print(str + "\t");
		}
		System.out.println();
		
		System.out.println(hashSet);
		
		
		
	}

}
