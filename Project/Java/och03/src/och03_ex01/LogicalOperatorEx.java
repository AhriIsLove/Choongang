package och03_ex01;

public class LogicalOperatorEx {

	public static void main(String[] args) {
		int iCharCode1 = 'A';
		int iCharCode2 = 'a';
		int iCharCode3 = '3';
		int iCharCode6 = 7;
		int iCharCode7 = 0;
		
		System.out.println("iCharCode1:" + iCharCode1);
		
		//& 와 &&는 동일한 AND 연산자 : | 와 ||도 동일
		//&&는 선행된 조건식이 false일 경우 뒷 조건식을 판별하지 않고 false 반환
		//&는 선행된 조건식이 false여도 뒷 조건식을 판별하고 false 반환
		if((iCharCode1 >= 65) & (iCharCode1 <= 90))
			System.out.println(iCharCode1 + "대문자입니다");

		if((iCharCode2 >= 97) && (iCharCode2 <= 122))
			System.out.println(iCharCode2 + "소문자입니다");

		if(!(iCharCode3 < 48) && !(iCharCode3 > 57))
			System.out.println(iCharCode3 + "숫자입니다");
		

		System.out.printf("iCharCode6 %c \n", iCharCode6);
		System.out.printf("iCharCode6 %d \n", iCharCode6);

		if((iCharCode6 % 2 == 0) | (iCharCode6 % 3 == 0))
			System.out.println("2 또는 3의 배수다");
		else
			System.out.println("2 또는 3의 배수가 아니다");
	}

}
