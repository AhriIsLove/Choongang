package och05_ex01;

public class Arr03 {

	public static void main(String[] args) {
		String[] arrStr = {"산토끼", "고양이", "판토끼", "강아지"};

		//일반 for문
		for(int i=0; i<arrStr.length; i++)
		{
			System.out.println((i+1) + "번째 값 : " + arrStr[i]);
		}
		
		System.out.println();
		
		//향상 for문
		for(String value : arrStr)
		{
			System.out.println(value);
		}
		
	}

}
