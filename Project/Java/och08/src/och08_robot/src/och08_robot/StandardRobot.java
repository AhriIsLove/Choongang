package och08_robot;

import och08_robot.actions.FireOk;
import och08_robot.actions.FlyNo;
import och08_robot.actions.KnifeWithWood;

public class StandardRobot extends Robot {

	public StandardRobot() {
		flyAction = new FlyNo();
		fireAction = new FireOk();

		//HW01_4
		//4.목검
		knifeAction = new KnifeWithWood();
	}
	
	@Override
	public void shape() {
		System.out.println("Robot 팔, 다리, 머리, 몸통. 달릴 수 있음");

	}

}
