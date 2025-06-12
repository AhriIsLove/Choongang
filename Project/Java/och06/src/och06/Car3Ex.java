package och06;

public class Car3Ex {

	public static void main(String[] args) {
		Car3 c1 = new Car3("소나타", 10, 14);
		Car3 c7 = new Car3();

		c7.m_strName = "투싼";
		c7.m_nInTime = 7;
		c7.m_nOutTime = 15;
		
		c1.Print();
		c7.Print();
		
	}

}
