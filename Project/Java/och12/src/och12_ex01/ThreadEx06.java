package och12_ex01;

//뭔가 다운로드했어...
import javax.swing.JOptionPane;

class C1 extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub
//		super.run();
		for(int i=0; i<10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class ThreadEx06 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		C1 c = new C1();
		c.start();
//		String name = JOptionPane;
	}

}
