package och11_ex03;

import java.util.HashMap;

public class HashMapEx01 {

	public static void main(String[] args) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		//  1.Key   중복허용 X
		//  2.Value 중복허용 O
		
		hashMap.put("김민재", "010-1234-5678");
		hashMap.put("손흥민", "010-7913-8462");
		hashMap.put("전지현", "010-0000-0000");
		hashMap.put("전지현", "010-1111-1111");//덮어쓰기
		hashMap.put("전지현3", "010-1111-1111");
		
		System.out.println("이승우 : " + hashMap.get("이승우"));
		System.out.println("김민재 : " + hashMap.get("김민재"));
		System.out.println("손흥민 : " + hashMap.get("손흥민"));
		System.out.println("전지현 : " + hashMap.get("전지현"));
		System.out.println("전지현3 : " + hashMap.get("전지현3"));

	}

}
