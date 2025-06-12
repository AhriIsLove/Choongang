package och08_ex04;

public class HankookTire extends Tire {
	public HankookTire(String location, int maxRotation) {
		super(location, maxRotation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean roll() {
//		return super.roll();
		++accumulatedRotation;
		if(accumulatedRotation < maxRotation) 
		{
			System.out.println("위치 : " + location + ", 남은 HankookTire Tire 수명 : " + (maxRotation - accumulatedRotation) + "회");
			return true;
		}
		else 
		{
			System.out.println("위치 : " + location + ", HankookTire 타이어 펑크!");
			return false;
		}
	}
}
