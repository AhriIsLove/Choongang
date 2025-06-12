package och04_ex01;

public class Sw01 {

	public static void main(String[] args) {
		int iNum = Integer.parseInt(args[0]);
		
		switch (iNum) {
			case 1: System.out.println("L"); break;
			case 2: System.out.println("O"); break;
			case 3: System.out.println("V"); break;
			case 4: System.out.println("E"); break;
			default:
				System.out.println("NONE");
				break;
		}
	}

}
