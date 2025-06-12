package och08_ex04;

public class Car {
	Tire frontLeft = new Tire("왼쪽 앞", 6);
	Tire frontRight = new Tire("오른쪽 앞", 2);
	Tire backLeft = new Tire("왼쪽 뒤", 3);
	Tire backRight = new Tire("오른쪽 뒤", 4);
	
	int run() {
		int returnNum = 0;
		
		System.out.println("[자동차가 달립니다]");
		if(!frontLeft.roll()) {
			stop();
			returnNum = 1;
			return returnNum;
		}
		if(!frontRight.roll()) {
			stop();
			returnNum = 2;
			return returnNum;
		}
		if(!backLeft.roll()) {
			stop();
			returnNum = 3;
			return returnNum;
		}
		if(!backRight.roll()) {
			stop();
			returnNum = 4;
			return returnNum;
		}
		
		return returnNum;
	}
	void stop() {
		System.out.println("[자동차가 멈춥니다]");
	}
}
