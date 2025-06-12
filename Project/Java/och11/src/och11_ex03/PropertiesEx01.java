package och11_ex03;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

public class PropertiesEx01 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties pt = new Properties();
		String path = PropertiesEx01.class.getResource("database.properties").getPath();
		
		System.out.println("1:" + path);

		//아스키코드 디코딩 '%20' -> 16진수(20) -> 공백문자(SP) -> ' '
		path = URLDecoder.decode(path,"utf-8");
		
		System.out.println("2:" + path);
		
		pt.load(new FileReader(path));
		
		String driver	 = pt.getProperty("driver");
		String url		 = pt.getProperty("url");
		String userName	 = pt.getProperty("userName");
		String password	 = pt.getProperty("password");
		
		System.out.println("driver : " + driver);
		System.out.println("url : " + url);
		System.out.println("userName : " + userName);
		System.out.println("password : " + password);
	}

}
