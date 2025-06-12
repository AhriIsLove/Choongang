package och08_ex04;

public class KumhoTire extends Tire {

	public KumhoTire(String location, int maxRotation) {
		super(location, maxRotation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean roll() {
		++accumulatedRotation;
		if(accumulatedRotation < maxRotation) 
		{
			System.out.println("위치 : " + location + ", 남은 KumhoTire Tire 수명 : " + (maxRotation - accumulatedRotation) + "회");
			return true;
		}
		else 
		{
			System.out.println("위치 : " + location + ", KumhoTire 타이어 펑크!");
			return false;
		}
	}
}
