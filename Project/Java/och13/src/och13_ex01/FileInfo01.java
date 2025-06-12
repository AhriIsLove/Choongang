package och13_ex01;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileInfo01 {

	public static void main(String[] args) throws IOException {
		File file = new File("src/och13_ex01/Buffer01.java");
		System.out.println("Directory : " 	+ file.isDirectory());
		System.out.println("절대경로 : " 	+ file.getAbsolutePath());
		System.out.println("표준경로 : " 	+ file.getCanonicalPath());//현재 절대 경로에서 해당 파일까지 경로(공통 폴더까지 올라간다)
		System.out.println("생성일 : " 	+ new Date(file.lastModified()));
		System.out.println("파일크기 : " 	+ file.length());
		System.out.println("읽기속성 : " 	+ file.canRead());
		System.out.println("쓰기속성 : " 	+ file.canWrite());
		System.out.println("파일경로 : " 	+ file.getParent());
		System.out.println("숨김속성 : " 	+ file.isHidden());

	}

}
