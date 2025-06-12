package och06;

class Inc {
	int m_num;
	static int s_num;
	
	public Inc() {
		m_num++;
		s_num++;
	}
	
	void Print() {
		System.out.println("m_num:" + m_num);
		System.out.println("s_num:" + s_num);
	}
}

public class NumEx {

	public static void main(String[] args) {
		Inc inc1 = new Inc();
		inc1.Print();
		Inc inc2 = new Inc();
		inc2.Print();
		Inc inc3 = new Inc();
		inc3.Print();

	}

}
