package och08_ex02;

public class CarEx {
	String kind;
	int kk;
	Engine eg;
	
	public CarEx(String kind, Engine eg)
	{
		this.kind = kind;
		this.eg = eg;
	}
	void print() {
		System.out.println("CarEx 종류 : " + kind);
		eg.print();
		System.out.println("---------");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Engine eg1 = new Engine("알파", 2000);
		Engine eg2 = new Engine("베타", 3000);
		
//		CarEx car1 = new CarEx("소나타", eg1);
//		CarEx car2 = new CarEx("모닝", eg1);
//		CarEx car3 = new CarEx("제네시스", eg2);
//		car1.print();
//		car2.print();
//		car3.print();
		
					// 배열의 new
		CarEx[] cars = new CarEx[3];
				//객체의 new
		cars[0] = new CarEx("소나타", eg1); 
		cars[1] = new CarEx("모닝", eg1);
		cars[2] = new CarEx("제네시스", eg2);
		
		for(CarEx c : cars)
		{
			c.print();
		}
		
	}

}
