package och08_ex01;

public class LenderAbleEx {

	public static void main(String[] args) {
		SeperateVolume sv = new SeperateVolume("삼국지");
		sv.CheckOut("김경민", "2025/04/17");
		sv.CheckIn();

	}

}
