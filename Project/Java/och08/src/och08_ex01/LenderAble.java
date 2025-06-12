package och08_ex01;

public interface LenderAble {
	int NORMAL = 0;//대여 X
	int BORROW = 1;//대여
	
	abstract void CheckOut(String borrower, String date);
	void CheckIn();
}

class SeperateVolume implements LenderAble {
	String title;
	String date;
	String borrower;
	int status;
	
	public SeperateVolume(String title) {
		this.title = title;
		status = NORMAL;
	}
	
	@Override
	//책 대여
	public void CheckOut(String borrower, String date) {
		if(status != NORMAL) return;
		
		this.borrower = borrower;
		this.date = date;
		status = BORROW;
		System.out.println(this.borrower + "이(가) " + this.date + "에 " + this.title + "을(를) 대여함.");
	}

	@Override
	public void CheckIn() {
		if(status != BORROW) return;

		System.out.println(borrower + "이(가) " + title + "을(를) 반납함.");
		title = null;
		borrower = null;
		date = null;
		status = NORMAL;
		
	}
	
}
