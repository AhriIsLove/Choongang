package och03_ex01;

public class ConditionalOperationEx {

	public static void main(String[] args) {
		int iScore = 75;
		
		//1. char grade 선언
		//2. 삼항연산자를 이용하여 score > 90 --> A등급입니다
		//3.				 아니면		--> B등급입니다

		char cGrade;
		
		cGrade = iScore > 90 ? 'A' : 'B';
		
		System.out.println(cGrade + "등급입니다");
		
	}

}
