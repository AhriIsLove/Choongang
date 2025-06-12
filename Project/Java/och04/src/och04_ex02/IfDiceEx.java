package och04_ex02;

public class IfDiceEx {

	public static void main(String[] args) {
		// 주사위
		int num = (int)(Math.random() * 6) + 1;

//		System.out.println(num + "번이 나왔습니다.");
		
		//굳이 if문으로...
		if(num == 1) System.out.println(num + "번이 나왔습니다.");
		else if(num == 2) System.out.println(num + "번이 나왔습니다.");
		else if(num == 3) System.out.println(num + "번이 나왔습니다.");
		else if(num == 4) System.out.println(num + "번이 나왔습니다.");
		else if(num == 5) System.out.println(num + "번이 나왔습니다.");
		else System.out.println(num + "번이 나왔습니다.");
		
	
	}

}
