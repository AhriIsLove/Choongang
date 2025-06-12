package och11_ex01;

import java.util.ArrayList;
import java.util.List;

public class ArrListHw01 {

	//Hw01
	//총 객체수: 5
	//List : [Java, JDBC, Database, Servlet/JSP, iBATIS]
	//2: Database
	public static void main(String[] args) {
		//interface = class
		List<String> list = new ArrayList<String>();
		list.add("Java");
		list.add("JDBC");
		list.add("Database");
		list.add("Servlet/JSP");
		list.add("iBATIS");
		
		System.out.println("총 객체수 : " + list.size());
		System.out.println("List : " + list);
		System.out.println("2 : " + list.get(2));
		
	}

}
