package och03_ex02;

public class Ex0202 {
	public static void main(String[] args) {
		int iScore = 85;
		String strResult;
		
		//삼항연산자 score>90 가 아니면 '가'
		//				그렇지 않으면 '나'
		
		strResult = !(iScore > 90) ? "가" : "나";
		
		System.out.println(strResult);
		
	}
}
