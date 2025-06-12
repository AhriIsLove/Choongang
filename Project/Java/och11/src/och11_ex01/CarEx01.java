package och11_ex01;

import java.util.ArrayList;

public class CarEx01 {

	public static void main(String[] args) {
		ArrayList<Car> arrayList = new ArrayList<Car>();
		
		arrayList.add(new Car());
		Bus bus = new Bus();
		arrayList.add(bus);
		arrayList.add(new Taxi());
		
		for(Car object : arrayList) {
			object.Print();
			
			if(object instanceof Bus) ((Bus)object).Move();
		}

	}

}
