package och04_ex01;

import java.io.IOException;

public class While02 {

	public static void main(String[] args) throws IOException {
		System.out.println("보고싶은 구구단은?");
		
		int iNum = System.in.read() - '0'/*48*/;//아스키 코드값으로 입력받음
		
		int i=1;
		if(iNum >= 2 && iNum <= 9)
		{
			while(i<=9)
			{
				System.out.println(iNum + "*" + i + "=" + iNum*i);
				i++;
			}
		}
		else
		{
			System.out.println("구구단에 없는 숫자:" + iNum);
		}
	}

}
