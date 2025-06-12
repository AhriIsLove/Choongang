package och07_ex03;

public class CalculatorEx {

	public static void main(String[] args) {
		Calculator cal = new Calculator();
		cal.powerOn();
		
		int result1 = cal.plus(5,6);
		System.out.println("result1:" + result1);
		
		int x = 10;
		int y = 4;
		double result2 = cal.divide(x,y);
		System.out.println("result2:" + result2);
		
		cal.powerOff();
	}

}
