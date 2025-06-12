package och13_ex01;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class FileWriter01 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("출력할 파일명 : ");
		String fileName = sc.nextLine();
		System.out.println("저장할 글을 입력하세요.");
		String msg = sc.nextLine();
		
//		FileWriter fileWriter = new FileWriter(fileName);
		FileWriter fileWriter = new FileWriter("src/och13_ex01/"+fileName);

		Date date = new Date();
		
		fileWriter.write("[" + date + "] " + msg);
		fileWriter.close();
		System.out.println("출력 완료");
		sc.close();
		
		
	}

}
