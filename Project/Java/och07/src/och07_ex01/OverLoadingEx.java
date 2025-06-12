package och07_ex01;

public class OverLoadingEx {

	public static void main(String[] args) {
		OverLoad ol = new OverLoad();
		ol.Print();
		ol.Print(12);
		ol.Print("대박");
		ol.Print("금요일", 16);

	}

}

class OverLoad {
	//HW01
	void Print() {
		System.out.println("OverLoad.Print 매개변수 없음");
	}
	void Print(int p_n) {
		System.out.println("OverLoad.Print 매개변수 1개 int:" + p_n);
	}
	void Print(String p_str) {
		System.out.println("OverLoad.Print 매개변수 1개 Str:" + p_str);
	}
	void Print(String p_str, int p_n) {
		System.out.println("OverLoad.Print 매개변수 2개 Str:" + p_str + ", int:" + p_n);
	}
}