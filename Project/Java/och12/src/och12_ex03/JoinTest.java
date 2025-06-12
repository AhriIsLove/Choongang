package och12_ex03;

import java.util.ArrayList;

class Before extends Thread{
	public Before(String str) {
		// TODO Auto-generated constructor stub
		super(str);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		System.out.println("Before:" + getName());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addCar();
	}
	void addCar() {
		System.out.println("Before.addCar");
		JoinTest.carList.add("벤츠");
		JoinTest.carList.add("제네시스");
		JoinTest.carList.add("SM5");
		JoinTest.carList.add("포르쉐");
	}
}

class After extends Thread{
	public After(String str) {
		// TODO Auto-generated constructor stub
		super(str);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		System.out.println("After:"+getName());
		for(String car : JoinTest.carList) {
			System.out.println(car);
		}
	}
}

public class JoinTest {
	public static ArrayList<String> carList = new ArrayList<String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Before before = new Before("우선");
		After after = new After("나중");
		
		before.start();
		
		try {
			//스레드가 끝날때 까지 기다린다.
			before.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		after.start();
		
	}

}
