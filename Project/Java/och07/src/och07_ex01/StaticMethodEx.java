package och07_ex01;

class StaticMethod{
	//static 선언시 객체 생성 없이 호출 가능 : 클래스를 사용하는 모든 객체의 변수/함수를 컨트롤 하기에 선언이 따로 필요 없음
	static int s_num = 0;
	int num = 0;
	
	void Display() {
		num++;
		System.out.println("num : " + num);
	}
	
	//static 선언시 객체 생성 없이 호출 가능 : 클래스를 사용하는 모든 객체의 변수/함수를 컨트롤 하기에 선언이 따로 필요 없음
	static void S_Print(String str)
	{
//		num++; //static 함수에서는 멤버변수 사용 불가능
		s_num++;
		System.out.println("우왕 " + str);
	}
}

public class StaticMethodEx {

	public static void main(String[] args) {
		StaticMethod.S_Print("생성 전");
		
		StaticMethod sm = new StaticMethod();
		sm.Display();
		sm.Display();
		sm.Display();

		StaticMethod.S_Print("생성 후");
		
		System.out.println("StaticMethod.s_num:" + StaticMethod.s_num);
		System.out.println("sm.s_num:" + sm.s_num);

		StaticMethod sm2 = new StaticMethod();
		System.out.println("sm2.s_num:" + sm2.s_num);
	}

}
