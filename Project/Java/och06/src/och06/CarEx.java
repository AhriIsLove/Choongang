package och06;

public class CarEx {

	public static void main(String[] args) {
		//클래스의 상속 상태 확인 : ctrl+T
		Car carA = new Car();
		carA.m_strColor = "red";
		carA.m_nSpeed = 200;
		
		carA.SpeedUp();
		carA.Print();
		
		Car carB = new Car();
		carB.m_strColor = "gray";
		carB.m_nSpeed = 150;
		
//		carB.SpeedUp();
		carB.Print();
	}

}
