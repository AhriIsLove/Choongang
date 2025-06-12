package och04_ex01;

import java.util.Scanner;

public class Do03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int iNum = 0;
		int iCnt = 1;
//		double dSystemAnswer = Math.random();
		int iSystemAnswer = (int) (Math.random()*100) + 1;
		
		while(true)
		{
			System.out.println("1~100 중 어떤 숫자일까요?");
			iNum = sc.nextInt();
			
			System.out.println("num:"+iNum);
			
			if(iNum == iSystemAnswer) 
			{
				System.out.println(iCnt + "번에 맞췄습니다.");
				break;
			}
			else if(iSystemAnswer > iNum) System.out.println("더 크다");
			else System.out.println("더 작다");
			
			iCnt++;
		}
		
		System.out.println("끝");
		
	}

}
