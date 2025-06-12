package och02_ex01;

public class CharEx {

	public static void main(String[] args) {
		char c1 = 'A';		//문자 저장
		char c2 = 65;		//10진수 저장
		char c3 = '\u0041';	//16진수로 저장
		char c4 = '가';		//한글 저장
		char c5 = 44032;	//10진수 저장 : 44032 부터 한글
		char c6 = '\uac00';	//16진수 저장
		
		char c7 = 0x41;	//16진수 저장
		
		System.out.println("c1:" + c1);
		System.out.println("c2:" + c2);
		System.out.println("c3:" + c3);
		System.out.println("c4:" + c4);
		System.out.println("c5:" + c5);
		System.out.println("c6:" + c6);
		
		System.out.println("c7:" + c7);
	}

}
