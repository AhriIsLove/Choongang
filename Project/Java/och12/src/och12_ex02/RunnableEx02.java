package och12_ex02;

class Th1 extends Thread {
	public Th1(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=1;i<=20;i++) {
			System.out.println(getName() + i);

			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		super.run();
	}
}
class Th2 extends Thread {
	public Th2(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=1;i<=20;i++) {
			System.out.println(getName() + i);

			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		super.run();
	}
}
class Ra3 implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=1;i<=20;i++) {
			System.out.println(Thread.currentThread().getName() + i);

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class Ra4 implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=1;i<=20;i++) {
			System.out.println(Thread.currentThread().getName() + i);

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class RunnableEx02 {

	public static void main(String[] args) {
		Ra3 r3 = new Ra3();
		Thread t3 = new Thread(r3, "대박");
		Ra4 r4 = new Ra4();
		Thread t4 = new Thread(r4, "월요일");
		Th1 t1 = new Th1("zz");
		Th2 t2 = new Th2("oo");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();

		// 1.Ra3  :  I/F 20번수행 --> getName()+대박+i
		// 2.Ra4  :  I/F 20번수행 --> getName()+월요일+i
		// 3.Th1  :  Thread 상속  , 수행 
		// 4.Th2  :  Thread 상속  , 수행

	}

}
