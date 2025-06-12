package och03_ex01;

public class StringEqualsEx {

	public static void main(String[] args) {
		String strVar1 = "신일섭선생님";//객체
		String strVar2 = "신일섭선생님";//만들어졌던 객체(strVar1이 받은 객체와 동일)
		String strVar3 = new String("신일섭선생님");//새로 만든 객체

		System.out.println(strVar1 == strVar2);
		System.out.println(strVar1 == strVar3);
		System.out.println();
		System.out.println("strVar1.equals(strVar2)" + strVar1.equals(strVar2));
		System.out.println("strVar1.equals(strVar3)" + strVar1.equals(strVar3));

	}

}
