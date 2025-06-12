package och09_ex03;

class Test {
	private static Test instance;
	public Test() {
		// TODO Auto-generated constructor stub
	}
	public Test getInstance() {
		if(instance == null) instance = new Test();
		a++;
		return instance;
	}
	
	private static int a = 0;
	
}

public class String01 {

	public static void main(String[] args) {
		String a1 = "Java";//String 객체에 문자열을 저장
		String a2 = "Java";
		String a3 = new String("Java");//문자열 상수를 생성자에 전달해서 String 객체를 생성한 경우
		String a4 = new String("java");
		String a5 = new String("Java");
		
		System.out.println(a1 + ", " + a2 + ", " + a3 + ", " + a4);
		if(a1 == a2) System.out.println("a1 == a2");
		else System.out.println("a1 != a2");
		if(a1 == a3) System.out.println("a1 == a3");
		else System.out.println("a1 != a3");
		if(a3 == a5) System.out.println("a3 == a5");
		else System.out.println("a3 != a5");
		
		System.out.println("");
		 
		if(a1.equals(a2)) System.out.println("a1 eqauls a2");
		else System.out.println("a1 !eqauls a2");
		if(a1.equals(a3)) System.out.println("a1 eqauls a3");
		else System.out.println("a1 !eqauls a3");
		if(a1.equals(a4)) System.out.println("a1 eqauls a4");
		else System.out.println("a1 !eqauls a4");
		if(a1.equalsIgnoreCase(a4)) System.out.println("a1 equalsIgnoreCase a4");
		else System.out.println("a1 !equalsIgnoreCase a4");
		if(a3.equalsIgnoreCase(a4)) System.out.println("a3 equalsIgnoreCase a4");
		else System.out.println("a3 !equalsIgnoreCase a4");

		System.out.println("");
		
		
		
//		Test test = null;
//
//		if(test.equals(test)) System.out.println("123");
//		else System.out.println("456");
	}

}

