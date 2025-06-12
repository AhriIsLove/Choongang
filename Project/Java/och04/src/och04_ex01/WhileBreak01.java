package och04_ex01;

public class WhileBreak01 {

	public static void main(String[] args) {
		int iNum = 0, iSum = 0;
		while(true)
		{
			iNum++;
			iSum += iNum;
			if(iNum == 5) break;
		}
		System.out.println("합계:" + iSum);
	}

}
