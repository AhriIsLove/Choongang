package och03_ex02;

public class Ex0201 {
	public static void main(String[] args) {
		int iX = 10;
		int iY = 20;
		int iZ = (++iX) + (iY--);

		System.out.println(iX);
		System.out.println(iY);
		System.out.println(iZ);
	}
}
