package och11_ex01;

import java.util.Vector;

public class VectorEx01 {

	public static void main(String[] args) {
		Vector<String> vector = new Vector<String>();

		vector.add("구렁2");
		vector.add("팔렁8");
		vector.add("칠렁7");
		vector.add("구렁2");
		vector.add("구렁3");
		vector.add("구렁9");
		for(int i=0;i<vector.size();i++) {
			System.out.print(vector.get(i) + "\t");
		}
		System.out.println();
		
		for(String str : vector) {
			System.out.print(str + "\t");
		}
	}

}
