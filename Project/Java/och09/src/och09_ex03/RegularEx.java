package och09_ex03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularEx {

	public static void main(String[] args) {
		String[] data = {"bat" , "baby", "bonus"
		       , "cA"  , "ca"  , "co"     , "c."
		       , "c0"  , "car" , "combat" , "count"
		       , "cafe12"  
		       , "date", "disc"};

		//정규표현식 : https://regex101.com
		//c로 시작하는 문자열 중 a-zA-Z만 해당하는 패턴
		Pattern p = Pattern.compile("c[a-zA-Z0-9]*");
		for(int i=0; i< data.length; i++)
		{
			//패턴에 해당하는 문자열인지 확인
			Matcher m = p.matcher(data[i]);
			if(m.matches())
			{
				System.out.println(i + ":" + data[i]);
			}
		}
	}

}
