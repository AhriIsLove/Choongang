package och04_ex02;

public class IfNestedEx {
	public static void main(String[] args) {
		int iScore = (int)(Math.random() * 20) + 81;//81 ~ 100
		System.out.println("점수 : " + iScore);
		
		//HW01
		//1. 2중 if문을 사용하여
		//2. 점수가 80~84 -> B
		//3. 점수가 85~89 -> B+
		//4. 점수가 90~94 -> A
		//5. 점수가 95~100 -> A+
		String strGrade = "";
		if(iScore >= 90)
		{
			strGrade = "A";
			if(iScore % 10 >= 5)
			{
				strGrade += "+";
			}
		}
		else if(iScore >= 80)
		{
			strGrade = "B";
			if(iScore % 10 >= 5)
			{
				strGrade += "+";
			}
		}
		
		System.out.println("학점 : " + strGrade);
	}
}
