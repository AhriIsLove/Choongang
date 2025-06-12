package och04_ex01;

public class If02 {

	/*
	 *args 입력방법                                     
	 *1.java파일 우클릭                                  
	 *2.Run As                                      
	 *3.Run Configurations...                       
	 *4.Arguments 탭                                 
	 *5.Program arguments 항목에 원하는 파라미터 입력(' '으로 구분) 
	 * */
	public static void main(String[] args) {
		//
		int iA = Integer.parseInt(args[0]);
		int iB = Integer.parseInt(args[1]);

		String strValue = args[2];
		
		System.out.println("A:" + iA);
		System.out.println("B:" + iB);
		System.out.println("str:" + strValue);

		if(iA > 0)
		{
			System.out.println(iA + "는 양수");
		}
		else
		{
			System.out.println(iA + "는 음수");
		}
		
		System.out.println("프로그램 종료");
	}

}
