package och12_ex01;

class Th1 extends Thread{
	@Override
	public void run() {
//		super.run();
		for(int i=0; i<=20; i++)
		{
			System.out.print("대박" + i + "\t");
			if(i % 5 == 0)
				System.out.println();
			
			try {
				sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class Th2 extends Thread{
	@Override
	public void run() {
//		super.run();
		for(int i=0; i<=20; i++)
		{
			System.out.print(getName() + i + "\t");
			if(i % 5 == 0)
				System.out.println();
			
			try {
				sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class ThreadEx03 {

	public static void main(String[] args) {
		Th1 thread1 = new Th1();
		Th2 thread2 = new Th2();
		
		thread1.start();
		thread2.start();

	}

}
