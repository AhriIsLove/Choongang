package och09_ex02;

import java.util.Random;

public class Random01 {

	public static void main(String[] args) {
		Random ran = new Random();
		
		System.out.println("100이하 정수 : " + ran.nextInt(101));
		System.out.println("랜덤 int : " + ran.nextInt());
		System.out.println("랜덤 double : " + ran.nextDouble());
		System.out.println("랜덤 float : " + ran.nextFloat());
		System.out.println("랜덤 long : " + ran.nextLong());
		

	}

}
