package och08_robot;

import och08_robot.actions.FireAction;
import och08_robot.actions.FlyAction;
import och08_robot.actions.KnifeAction;

public abstract class Robot {
	public FireAction	fireAction;
	public FlyAction	flyAction;
	public KnifeAction	knifeAction;
	//HW01_2
	//2.나이프 객체 선언
	//2-1. 나이프 행동 정의
	
	public Robot() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract void shape();
	
	public void actionFire() {
		fireAction.fire();
	}
	public void actionFly() {
		flyAction.fly();
	}
	public void actionKnife() {
		knifeAction.knife();
	}
	public void actionBasic() {
		System.out.println("Robot 팔, 다리, 머리, 몸통");
	}
}
