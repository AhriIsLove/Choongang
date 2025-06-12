package och09_ex02;

public class Integer01 {

	public static void main(String[] args) {
		String	str		= "3";
		int		n1		= Integer.parseInt("3");
		Integer	int1	= Integer.valueOf(str);
		
		System.out.println(n1 + int1);
		System.out.println(str + str);
		System.out.println(n1 + str);
	}

}
