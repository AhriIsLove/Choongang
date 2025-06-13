package och08_robot;

//import och08_robot.actions.FireNo;
import och08_robot.actions.FireOk;
import och08_robot.actions.FlyNo;
import och08_robot.actions.KnifeNo;

public class CheapRobot extends Robot {

	public CheapRobot() {
		flyAction = new FlyNo();
		//strategy 패턴 예시
//		fireAction = new FireNo();
		fireAction = new FireOk();

		//HW01_3
		//3.검 없음
		knifeAction = new KnifeNo();
	}
	
	@Override
	public void shape() {
		// TODO Auto-generated method stub
		System.out.println("Robot 팔, 다리, 머리, 몸통");
	}

}
