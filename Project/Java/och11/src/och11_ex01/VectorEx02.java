package och11_ex01;

import java.util.Vector;

public class VectorEx02 {

	public static void main(String[] args) {
		Vector<String> vector = new Vector<String>();
		vector.add("수박");
		vector.add("고추");
		vector.add("수박");
		vector.add("대추");
		vector.add("오이");
		vecPrint(vector);
		
		// 1번 자리에 키위 끼워 넣기
		vector.add(1, "키위");
		vecPrint(vector);
		
		// 4번 봉숭아 바꾸기
		vector.set(4, "봉숭아");
		vecPrint(vector);
		
		// 0번 삭제하기
		vector.remove(0);
		vecPrint(vector);
		
		vector.add("수박");
		
		System.out.println("2 : " + vector.get(2));
		System.out.println("수박 여부 : " + vector.contains("수박"));
		System.out.println("첫 수박 : " + vector.indexOf("수박"));
		System.out.println("마지막 수박 : " + vector.lastIndexOf("수박"));
		
		vecPrint(vector);

		// 수박을 바나나로 교체
		for(int i=0; i<vector.size(); i++) {
			if(vector.get(i).equals("수박")) vector.set(i, "바나나");
		}
		vecPrint(vector);
		
	}
	
	public static void vecPrint(Vector<String> pVector) {
		System.out.println("개수 : " + pVector.size());
		for(String str : pVector) {
			System.out.print(str + "\t");
		}
		System.out.println();
	}

}
