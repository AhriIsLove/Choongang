package och04_ex03;

import java.util.Scanner;

public class Ex0302 {

	public static void main(String[] args) {
		boolean run  = true;
		int balance = 0;
		
		Scanner sc = new Scanner(System.in);
		
		while(run)
		{
			System.out.println("-----------------------------");
			System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.종료");
			System.out.println("-----------------------------");
			System.out.print("선택 : ");
			int menuNum = sc.nextInt();
			
			// HW06
			// menuNum->1
			//   1) balance = balance + 예금액
			// menuNum->2
			//   2) balance = balance - 출금액
			// menuNum->3
			//   잔고 조회 콘솔에 보여줌
			
			int money = 0;
			switch (menuNum) {
			case 1:
				//예금
				System.out.print("1.예금 : ");
				money = sc.nextInt();
				balance += money;
				break;
			case 2:
				//출금
				System.out.print("2.출금 : ");
				money = sc.nextInt();
				balance -= money;
				break;
			case 3:
				//잔고
				System.out.println("3.잔고 : " + balance);
				break;
			default:
				run = false;
				break;
			}
		}
		
		System.out.println("프로그램 종료");
		
		sc.close();
	}

}
