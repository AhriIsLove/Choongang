package och09_ex03;

public class String03 {

	public static void main(String[] args) {
		String str1 = 12+"";
		String str2 = String.valueOf(3);

		if(str1 instanceof String) System.out.println("문자열O:" + str1);
		else System.out.println("문자열X:" + str1);
		if(str2 instanceof String) System.out.println("문자열O:" + str2);
		else System.out.println("문자열X:" + str2);
		
		char c1 = str1.charAt(0);
		char c2 = str1.charAt(1);

		System.out.println("c1:"+c1);
		System.out.println("c2:"+c2);

	}

}
