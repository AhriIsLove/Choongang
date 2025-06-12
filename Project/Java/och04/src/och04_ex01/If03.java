package och04_ex01;

public class If03 {
	public static void main(String[] args) {
		// HW02
		// 1. If03 수행시 파라메타를 하나받음(점수)
		// 2.score 가지고 if문 수행 
		//    1) 90 <= X          grade = "A";
		//    2) 80 <= X <  90    grade = "B";
		//    3) 70 <= X <  80    grade = "C";
		//    4) ELSE             grade = "F";
		//  당신점수는85 이고 학점은 B입니다
		
		int iScore = Integer.parseInt(args[0]);
		String strGrade;

		if(90 <= iScore) strGrade = "A";
		else if(80 <= iScore) strGrade = "B";
		else if(70 <= iScore) strGrade = "C";
		else strGrade = "F";
		
		System.out.println("당신점수는" + iScore + "이고 학점은 " + strGrade + "입니다");
	}
}
