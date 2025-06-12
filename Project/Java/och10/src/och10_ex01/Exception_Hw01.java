package och10_ex01;

import java.util.Scanner;

public class Exception_Hw01 {

	public static void main(String[] args) {
		int result = 0;
		int num1 = 0;
		int num2 = 0;
		Scanner sc = new Scanner(System.in);
		
		//Hw01
		//첫번째 숫자 입력하세요
		//aa
		//나머지 Error
		//난 무조건 나온다
		//프로그램 종료

		//첫번째 숫자 입력하세요
		//12
		//두번째 숫자 입력하세요
		//0
		//숫자만  가능 합니다 ㅎㅎ 
		//난 무조건 나온다
		//첫번째 숫자 입력하세요
		
		do {
			try {
				System.out.print("첫 번째 값을 입력하세요.");
				num1 = Integer.parseInt(sc.nextLine());	
				if(num1 == 0)break;
				System.out.print("두 번째 값을 입력하세요.");
				num2 = Integer.parseInt(sc.nextLine());
				
				result = num1 / num2;
				System.out.printf("%d / %d = %d\n", num1, num2, result);
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("나머지 Error");
				break;
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자만 가능합니다 ㅎㅎ");
			} finally {
				System.out.println("난 무조건 나온다");
			}
		}while(true);
		
		System.out.println("프로그램 종료");
		
		sc.close();

	}

}
