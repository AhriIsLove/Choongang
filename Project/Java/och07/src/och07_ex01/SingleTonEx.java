package och07_ex01;

class Singleton {
	//             데이터타입   데이터명
	private static Singleton instance;
	// 생성자는 외부에서 호출하지 않고 방지하기 위해 private
	private Singleton() {
		
	}
	public static Singleton GetInstance() {
		//처음이면 만들어서
		if(instance == null) instance = new Singleton();
		//반환
		return instance;
	}
	
	String str;
}

public class SingleTonEx {

	public static void main(String[] args) {
		Singleton st1 = Singleton.GetInstance();
		Singleton st2 = Singleton.GetInstance();
		
		st1.str = "st1";
		System.out.println(st2.str);

		if(st1 == st2) System.out.println("같다");
		else System.out.println("다르다");

	}

}
