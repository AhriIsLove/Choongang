package och08_ex02;

public class Engine {
	String type;//알파, 베타
	int cc;//2000, 3000
	
	public Engine(String type, int cc) {
		this.type = type;
		this.cc = cc;
	}
	
	void print() {
		System.out.println("타입 : " + type);
		System.out.println("배기량 : " + cc);
	}
}
