package och06;

public class Car3 {
	String m_strName;//차량번호
	int m_nInTime;//입고시간
	int m_nOutTime;//출고시간
	int m_nFee;//주차요금
	
	final int AMT_PER_TIME = 3000;//시간당 요금
	
	public Car3() {
		System.out.println("생성자");
	}
	public Car3(String p_strNmae, int p_nInTime, int p_nOutTime) {
		System.out.println("생성자_파라미터");
		this.m_strName = p_strNmae;
		this.m_nInTime = p_nInTime;
		this.m_nOutTime = p_nOutTime;
	}
	
	void Print()
	{
		m_nFee = (m_nOutTime - m_nInTime) * AMT_PER_TIME;
		
		System.out.println("---------------------");
		System.out.println("차량번호 : " + m_strName);
		System.out.println("입고시간 : " + m_nInTime);
		System.out.println("출고시간 : " + m_nOutTime);
		System.out.println("주차요금 : " + m_nFee);
		System.out.println("---------------------");
	}
}
