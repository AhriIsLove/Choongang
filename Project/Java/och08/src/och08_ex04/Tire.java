package och08_ex04;

public class Tire {
	public int maxRotation;//최대 회전수
	public int accumulatedRotation;//누적 회전수
	public String location;//위치
	
	public Tire(String location, int maxRotation) {
		this.location = location;
		this.maxRotation = maxRotation;
	}
	
	public boolean roll() {
		++accumulatedRotation;
		if(accumulatedRotation < maxRotation) 
		{
			System.out.println("위치 : " + location + ", 남은 Tire 수명 : " + (maxRotation - accumulatedRotation) + "회");
			return true;
		}
		else 
		{
			System.out.println("위치 : " + location + ", 타이어 펑크!");
			return false;
		}
	}
}
