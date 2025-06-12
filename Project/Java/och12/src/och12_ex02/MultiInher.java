package och12_ex02;

class Car {
	int k = 0;
}

//원칙1
//Thread 생성시 반드시 extends Thread
//1. 누군가 상속시 extends Thread불가 하기 때문에  
//2. 추가로 implements Runnable I/F 상속 구현
class Multi extends Car implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=1; i<=10; i++) {
			System.out.print("multi" + i + "\t");
			if(i%10 == 0) System.out.println();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class MultiInher {

	public static void main(String[] args) {
		Multi m1 = new Multi();
//		m1.start();//없음

		// implement 한 것은 반드시 아래와 같은 선언후 사용
		// Must --> Thread th1 = new thread(r1)
		Thread multi1 = new Thread(m1);
		multi1.start();
		
		Thread multi2 = new Thread(m1, "pr");// ㅠㅠ
		multi2.start();
		
	}

}
