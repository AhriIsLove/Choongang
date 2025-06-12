package och11_ex01;

public class Car {
	void Print() {
		System.out.println("I'm a Car");
	}
}

class Bus extends Car{
	@Override
	void Print() {
		// TODO Auto-generated method stub
//		super.Print();
		System.out.println("I'm a Bus!!!");
	}
	void Move() {
		System.out.println("승객 50명!");
	}
}
class Taxi extends Car{
	@Override
	void Print() {
		// TODO Auto-generated method stub
//		super.Print();
		System.out.println("I'm a Taxi!");
	}
}
