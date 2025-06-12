package och13_ex01;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileReader01 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("파일명을 입력");
		String fileName = sc.nextLine();
		FileReader fileReader = new FileReader("src/och13_ex01/" + fileName);
		
		int data = 0;
		while ((data=fileReader.read()) != -1) {
			System.out.print((char)data);
		}
		fileReader.close();
		
		System.out.println("출력끝");
		
		sc.close();
		
	}

}
