package och11_ex01;

import java.util.Stack;

public class StackEx01 {

	public static void main(String[] args) {
		String[] kimchi = {"김치", "깍두기", "파김치", "김치", "겉절이"};
		Stack<String> stack = new Stack<String>();
		for(String str : kimchi) {
			stack.push(str);
		}
		
		System.out.println("stack : " + stack);

//		//스택인데 이게 가능하네
//		stack.add(2, "띠용");
//		System.out.println("stack : " + stack);
		
		//선입후출로 조회하며 삭제함
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + "\t");
		}
		System.out.println("");
		
		System.out.println("stack : " + stack);
		
		
		
	}

}
