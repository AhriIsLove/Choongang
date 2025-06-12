package och11_ex01;

import java.util.ArrayList;

public class ArrayList01 {

	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList<String>();
		
		arrayList.add("구렁2");
		arrayList.add("구렁8");
		arrayList.add("구렁2");
		arrayList.add("구렁4");
		arrayList.add("구렁3");
		arrayList.add("구렁1");
		
		for(int i=0; i< arrayList.size(); i++)
		{
			System.out.println(i + " : " + arrayList.get(i));
		}
		System.out.println();
		for(String str : arrayList)
		{
			System.out.println(str);
		}


	}

}
