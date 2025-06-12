package och09_ex03;

public class String02 {

	public static void main(String[] args) {
		char[] c = {'k', 'o', 'r', 'e', 'a'};
		String str1 = new String(c);
		String str2 = new String("Fighting");
		String str3 = str1 + str2;
		
		System.out.println("str1:" + str1);
		System.out.println("str2:" + str2);
		System.out.println("str3:" + str3);
		
		System.out.println();
		
		System.out.println(2+0+2+5+"년");
		System.out.println("연1:"+2+0+2+5);
		System.out.println("연2:"+(2+0+2+5));

	}

}
