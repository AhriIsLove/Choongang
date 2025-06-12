package och13_ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Buffer01 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("이름은?");
		String name = reader.readLine();
		
		System.out.println("주소는?");
		String addr = reader.readLine();
		
		System.out.println(name + "은(는) " + addr + "에 살아요.");
		
	}

}
