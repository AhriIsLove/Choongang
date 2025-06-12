package och04_ex01;

import java.io.IOException;

public class For02 {

	public static void main(String[] args) throws IOException {
		System.out.println("보고싶은 구구단은?");
		
		int iNum = System.in.read() - '0'/*48*/;//아스키 코드값으로 입력받음

		for(int i=1; i<10; i++)
		{
			System.out.println(iNum + "*" + i + "=" + iNum*i);
		}
	}

}
