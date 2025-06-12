package och12_ex01;

class A1 extends Thread {
	public A1(String str) {
		super(str);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
//		super.run();
		for(int i=0; i<=100; i++)
		{
			//스레드의 이름을 출력
			System.out.print(getName() + i + "\t");
			if(i % 10 == 0)
				System.out.println();
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		super.run();
	}
}

public class ThreadEx01 {

	public static void main(String[] args) throws InterruptedException {
		A1 theradA = new A1("subA");
		A1 theradB = new A1("subB");
		
		//스레드 시작
		theradA.start();
		theradB.start();
		
		//메모리를 다 먹어버려요
//		theradA.run();
//		theradB.run();
		
		for(int i=1; i<=100;i++)
		{
			System.out.print("Main" + i + "\t");
			if(i % 10 == 0)
				System.out.println();
			
			Thread.sleep(30);
		}
		

	}

}
