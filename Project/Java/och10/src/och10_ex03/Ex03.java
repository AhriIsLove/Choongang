package och10_ex03;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ex03 {

	public static void main(String[] args) {
		FileReader reader;
		char[] buffer = new char[1000];
		String fileName = ".classpath";
		
		try {
			reader = new FileReader(fileName);//프로젝트 폴더에서 찾는다.
			
			reader.read(buffer, 0, 900);
			String str = new String(buffer);
			System.out.println("읽음 : " + str);
			
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("파일 없음");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("읽지 못함");
			e.printStackTrace();
		} finally {
			System.out.println("시도는 했습니다만...");
		}

	}

}
