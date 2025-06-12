package och07_ex03;

//HW02
public class Calculator {
	void powerOn()
	{
		System.out.println("전원을 켭니다.");
	}
	void powerOff()
	{
		System.out.println("전원을 끕니다.");
	}
	
	int plus(int x, int y)
	{
		return x + y;
	}
	double divide(double x, double y)
	{
		if(y == 0) return -1;
		
		return x / y;
	}
}
