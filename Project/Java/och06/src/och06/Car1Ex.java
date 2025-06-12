package och06;

// java의 클래스 파일내에는 클래스 파일 명과 동일한 이름의 클래스 1개만 정의 가능
class Car1 {
	//공유변수
	static int m_nSpeed;
	//멤버변수
	String m_strColor;
	int m_nWheel;

	//메소드
	void SpeedUp()
	{
		m_nSpeed++;
		System.out.println(m_nSpeed + "속도를 올렸다.");
	}
	void Print()
	{
		System.out.println("색깔 : " + m_strColor);
		System.out.println("속도 : " + m_nSpeed);
		System.out.println("-----------------");
	}
}

public class Car1Ex {

	public static void main(String[] args) {
		//Car1의 모든 객체의 Speed를 설정
		Car1.m_nSpeed = 100;
		
		Car1 car1A = new Car1();
		Car1 car1B = new Car1();
		
		car1A.Print();
		car1A.m_strColor = "red";
		car1B.m_strColor = "yellow";

		//Car1의 모든 객체의 Speed를 설정
		car1A.m_nSpeed = 100;
		
		car1A.Print();
		car1B.Print();
	}

}
