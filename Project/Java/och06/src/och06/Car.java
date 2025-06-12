package och06;

public class Car {
	//멤버변수
	String m_strColor;
	int m_nSpeed;
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
	}
}
