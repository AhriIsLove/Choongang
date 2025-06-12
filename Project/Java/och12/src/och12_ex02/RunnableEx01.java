package och12_ex02;

class Ra1 implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=1; i<=20; i++) {
			System.out.println(i + "대박" + Thread.currentThread().getName());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class Ra2 implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=1; i<=20; i++) {
			System.out.println(i + "화요일" + Thread.currentThread().getName());
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class RunnableEx01 {

	public static void main(String[] args) {
		Ra1 ra1 = new Ra1();
		Thread th1 = new Thread(ra1, "쓰레드1");
		Ra2 ra2 = new Ra2();
		Thread th2 = new Thread(ra2, "쓰레드2");
		
		th1.start();
		th2.start();
		
	}

}


//싱글턴 클래스 생성 연습
class Ins {
	private static Ins instance;
	private Ins() {
		// TODO Auto-generated constructor stub
	}
	public static Ins getInsatnce() {
		if(instance == null) instance = new Ins();
		return instance;
	}
}






