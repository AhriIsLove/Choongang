package och12_ex03;

class G1 extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		for(int i=0; i<20; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
}

class G2 implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<20; i++) {
			System.out.print("+");
		}
		System.out.println();
	}	
}

class G3 implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<20; i++) {
			System.out.print("*");
		}
		System.out.println();
	}	
}

public class Ex01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		G1 g1 = new G1();
		G2 g2 = new G2();
		G3 g3 = new G3();
		
		g1.start();
		Thread thread2 = new Thread(g2);
		thread2.start();
		Thread thread3 = new Thread(g3);
		thread3.start();
	}

}
