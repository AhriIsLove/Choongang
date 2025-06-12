package och08_ex04;

public class CarEx {

	public static void main(String[] args) {
		Car car = new Car();
		
		for(int i=0; i<10; i++)
		{
			int problemLocation = car.run();
			
			switch (problemLocation) {
			case 1:
				System.out.println(car.frontLeft.location + " HankookTire로 교체");
				car.frontLeft = new HankookTire(car.frontLeft.location, 15);
				break;
			case 2:
				System.out.println(car.frontRight.location + " KumhoTire로 교체");
				car.frontRight = new KumhoTire(car.frontRight.location, 13);
				break;
			case 3:
				System.out.println(car.backLeft.location + " HankookTire로 교체");
				car.backLeft = new HankookTire(car.backLeft.location, 14);
				break;
			case 4:
				System.out.println(car.backRight.location + " KumhoTire로 교체");
				car.backRight = new KumhoTire(car.backRight.location, 17);
				break;
			default:
				break;
			}
			System.out.println("---------");
		}

	}

}
