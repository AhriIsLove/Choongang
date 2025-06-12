package och04_ex02;

public class SwitchStringEx {

	public static void main(String[] args) {
		String position = args[0];
		
		int pay = 0;
		// Hw03
		// 부장 -> 700만원
		// 과장 -> 500만원
		// 그외 -> 300만원
		switch(position)
		{
		case "부장": pay = 700;
			break;
		case "과장": pay = 500;
			break;
		default: pay = 300;
			break;
		}
		
		System.out.println(position + "은 " + pay + "만원");

	}

}
