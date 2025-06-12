package och12_ex03;

public class Account2 {
	int total;//계좌 금액
	
	public Account2(int total) {
		// TODO Auto-generated constructor stub
		this.total = total;
	}
	public int getTotal() {
		System.out.println("잔액 : " + total);
		return total;
	}
	
	//예금
	void deposit(int amt, String name) {
		total += amt;
		System.out.println(name + "의 예금 : " + amt);
	}
	void withDraw(int amt, String name) {
		if(amt <= total) {
			total -= amt;
			System.out.println(name + "의 출금 : " + amt);
		}
		else {
			System.out.println("잔액 부족! " + name + "의 출금요청 : " + amt);
		}
		getTotal();
	}
}
