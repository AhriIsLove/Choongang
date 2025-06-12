package och03_ex01;

public class ConditionalOperationEx2 {

	public static void main(String[] args) {
		int iScore = 75;
		
		//1. char grade 선언
		//2. 삼항연산자를 이용하여 score > 90 --> score점은 A등급입니다
		//3.				 score > 80	--> score점은 B등급입니다
		//4.				 아니면		--> score점은 C등급입니다

		char cGrade;
		
		cGrade = iScore > 90 ? 
				'A' : (iScore > 80 ? 'B' : 'C');
		
		System.out.println(iScore + "점은 " + cGrade + "등급입니다");
		
	}

}
