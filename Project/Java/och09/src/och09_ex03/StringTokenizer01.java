package och09_ex03;

import java.util.StringTokenizer;

public class StringTokenizer01 {

	public static void main(String[] args) {
												//문자열, 구분자들
		StringTokenizer st = new StringTokenizer("산딸기,        집딸기/판딸기,집딸기\n알카리딸기\t뚜왈기\t딸기쥬스/딸기원\\딸기투", ".,\t\\ ");
		
		while (st.hasMoreElements()) {
//			Object object = (Object) st.nextElement();
			System.out.println(st.nextElement());
		}
	}

}
