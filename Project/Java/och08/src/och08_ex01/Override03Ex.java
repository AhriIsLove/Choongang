package och08_ex01;

//추상 클래스 : 하나이상의 추상 메소드가 존재 해야 함
abstract class AbClass01 {
	final static int FSNUM = 9;
	int k;
	
	public AbClass01() {
		System.out.println("추상클래스:AbClass01 생성자");
	}

	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	
	//추상 메소드 : 사용하기 위해선 클래스가 추상클래스여야 함
	abstract void print();
}

//구현용 클래스
class ImClass01 extends AbClass01 {

	public ImClass01() {
		System.out.println("구현클래스:ImClass01 생성자");
	}
	
	//추상클래스에서 정의한 추상메소드 구현 필요
	@Override
	void print() {
		System.out.println("추상메소드 구현!" + k + " | " + FSNUM);
		
	}
	
	void print2() {
		System.out.println("ImClass의 메소드");
	}
	
}

public class Override03Ex {

	public static void main(String[] args) {
		//사용하기 위해 추상 메소드를 구현해야 함
		AbClass01 abClass1 = new AbClass01() {
			@Override
			void print() {
				// TODO Auto-generated method stub
				
			}
		};

		ImClass01 imClass1 = new ImClass01();
		imClass1.setK(10);
		imClass1.print();
		imClass1.print2();
		
		AbClass01 abClass2 = new ImClass01();
		abClass2.setK(12);
		abClass2.print();
//		abClass2.print2();
		
	}

}
