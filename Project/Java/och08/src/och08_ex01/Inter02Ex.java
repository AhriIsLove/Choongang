package och08_ex01;

public class Inter02Ex {

	public static void main(String[] args) {
		Inter02Class ic2 = new Inter02Class();
		ic2.FirstMethod();
		ic2.SecondMethod();
		ic2.ThirdMethod();

		//선언은 클래스의 상속자들로 선언 가능
		Inter02_2 interface2 = new Inter02Class();//얘는 Inter02_2인거임
		interface2.FirstMethod();
		interface2.SecondMethod();
//		interface2.ThirdMethod();//불가능
		
		
		
	}

}
