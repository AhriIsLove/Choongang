package och11_ex03;

import java.util.Hashtable;

public class HashTableEx01 {

	public static void main(String[] args) {
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		
		hashtable.put("조정은", "010-1111-1111");
		hashtable.put("손흥민", "010-2222-2222");
		hashtable.put("황희찬", "010-3333-3333");
		System.out.println("조정은 : " + hashtable.get("조정은"));
		System.out.println("손흥민 : " + hashtable.get("손흥민"));
		System.out.println("황희찬 : " + hashtable.get("황희찬"));

		
	}

}
