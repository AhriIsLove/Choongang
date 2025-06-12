package och12_ex01;

public class ThreadEx04_Hw03 {

	public static void main(String[] args) {
		Th3 thread1 = new Th3("Th3...");
		Th4 thread2 = new Th4("Th4...");
		
		thread1.start();
		thread2.start();

	}

}

//Hw03
// 20번씩 수행
// Th3 -> 10 / 1000 쉼 
// Th4 -> 20 / 1000 쉼

//Th3
class Th3 extends Thread {
	public Th3(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<20; i++)
		{
			System.out.println(getName() + "화요일" + i);
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		super.run();
	}
}
//Th4
class Th4 extends Thread {
	public Th4(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<20; i++)
		{
			System.out.println(getName() + "대박" + i);
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		super.run();
	}
}