package och13_ex01;

import java.io.File;

public class Dir01 {

	public static void main(String[] args) {
		File file = new File(".");

		String[] list = file.list();
		
		for(String str : list)
			System.out.println(str);
		
		showFiles(file);

		file = new File("src/och13_ex01");

		showFiles(file);


	}

	
	public static void showFiles(File file)
	{
		String[] list = file.list();
		
		for(String str : list)
			System.out.println(str);
	}
}
