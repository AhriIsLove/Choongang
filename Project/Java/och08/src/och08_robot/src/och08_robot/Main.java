package och08_robot;

public class Main {

	public static void main(String[] args) {
		System.out.println("SuperRobot 주문!");
		Robot superRobot = new SuperRobot();
		superRobot.shape();
		superRobot.actionBasic();
		superRobot.actionFly();
		superRobot.actionFire();
		//HW01_6
		//6.검 휘두르기
		superRobot.actionKnife();
		

		System.out.println("---------");
		
		System.out.println("StandardRobot 주문!");
		Robot standardRobot = new StandardRobot();
		standardRobot.shape();
		standardRobot.actionBasic();
		standardRobot.actionFly();
		standardRobot.actionFire();
		//HW01_7
		//7.검 휘두르기
		standardRobot.actionKnife();

		System.out.println("---------");
		
		System.out.println("CheapRobot 주문!");
		Robot cheapRobot = new CheapRobot();
		cheapRobot.shape();
		cheapRobot.actionBasic();
		cheapRobot.actionFly();
		cheapRobot.actionFire();
		//HW01_8
		//8.검 휘두르기
		cheapRobot.actionKnife();

	}

}
