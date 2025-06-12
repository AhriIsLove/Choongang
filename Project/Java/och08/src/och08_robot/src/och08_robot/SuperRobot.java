package och08_robot;

import och08_robot.actions.FireOk;
import och08_robot.actions.FlyOk;
import och08_robot.actions.KnifeWithLazer;

public class SuperRobot extends Robot {

	public SuperRobot() {
		flyAction = new FlyOk();
		fireAction = new FireOk();

		//HW01_5
		//5.레이저검
		knifeAction = new KnifeWithLazer();
	}
	
	@Override
	public void shape() {
		System.out.println("Robot 팔, 다리, 머리, 몸통. 날 수 있음");

	}

}
