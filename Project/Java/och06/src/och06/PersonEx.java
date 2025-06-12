package och06;

class Person {
	String name;
	int age;
	String gender;
	
	void Print() {
		System.out.println("이름:" + name + "\t나이:" + age + "\t성별:" + gender);
	}
}

public class PersonEx {

	public static void main(String[] args) {
		Person per11 = new Person();
		Person per12 = new Person();
		Person per13 = new Person();
		
		per11.name = "홍길동"; per11.age = 52; per11.gender = "M";
		per12.name = "연개소문"; per12.age = 62; per12.gender = "M";
		per13.name = "선덕여왕"; per13.age = 72; per13.gender = "F";

		per11.Print();
		per12.Print();
		per13.Print();
	}

}
