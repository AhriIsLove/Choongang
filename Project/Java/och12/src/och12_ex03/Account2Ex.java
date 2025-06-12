package och12_ex03;

class Account2User extends Thread {
	Account2 act;
	boolean flag;
	
	public Account2User(Account2 act, String name) {
		// TODO Auto-generated constructor stub
		super(name);
		this.act = act;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for(int i=0; i< 5; i++)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//하나의 자원(변수)를 여러 스레드가 사용하려 할때 하나의 스레드만 사용할 수 있도록(데드락 방지)
			synchronized (act) {
				if(flag) act.withDraw((int)(Math.random()*10000), getName());
				else	 act.deposit((int)(Math.random()*10000), getName());
				flag = !flag;
			}
		}
	}
}

public class Account2Ex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//그룹별 공용 계좌 생성
		Account2 actA = new Account2(10000);
		Account2 actB = new Account2(15000);
		
		//A그룹 계좌 사용자 생성
		Account2User user1 = new Account2User(actA, "김준수");
		Account2User user2 = new Account2User(actA, "조정은");
		Account2User user3 = new Account2User(actA, "전동석");
		
		//B그룹 계좌 사용자 생성
		Account2User user4 = new Account2User(actB, "정국");
		Account2User user5 = new Account2User(actB, "뷔");
		Account2User user6 = new Account2User(actB, "지민");
		
		//사용 시작
		user1.start();
		user2.start();
		user3.start();
		user4.start();
		user5.start();
		user6.start();
		
		
	}

}
