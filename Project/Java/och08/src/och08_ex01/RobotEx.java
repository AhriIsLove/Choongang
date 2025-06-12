package och08_ex01;

public class RobotEx {

	static void Action(Robot r) {
		//instanceof : 객체 비교 연산자
		if(r instanceof DanceRobot)
		{
			DanceRobot dr = (DanceRobot)r;
			dr.Dance();
		}
		else if(r instanceof DrawRobot)
		{
			((DrawRobot) r).Draw();
		}
		else if(r instanceof SingRobot)
		{
			SingRobot sr = (SingRobot)r;
			sr.Sing();
		}
		else
		{
			//역할없는 로봇
			System.out.println("역할 없음" + DanceRobot.class.getInterfaces());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Robot[] r = new Robot[4];
		r[0] = new DanceRobot();
		r[1] = new DrawRobot();
		r[2] = new SingRobot();
		r[3] = new Robot() {
		};
		
		
		for(Robot value : r)
		{
			Action(value);
		}
		
	}

}
