package och04_ex02;

public class SwitchNoBreakCaseEx {

	public static void main(String[] args) {
		// 8 <= … < 12(8+4) 사이의 정수 얻기   --> random
		int time = (int)(Math.random() * 4) + 8;
		
		System.out.println("[현재시간:" + time + "시]");
		
		switch(time)
		{
			case 8: System.out.println("출근을 합니다.");
//				break;
			case 9: System.out.println("회의를 합니다.");
//				break;
			case 10: System.out.println("업무를 합니다.");
//				break;
			default: System.out.println("외근을 나갑니다.");
//				break;
		}

	}

}
