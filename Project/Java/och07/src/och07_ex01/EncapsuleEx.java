package och07_ex01;

class EncapsuleB
{
	String name;
	int age;
}

public class EncapsuleEx {

	public static void main(String[] args) {
		EncapsuleA cap = new EncapsuleA();
		
//		cap.name = "김준수";
//		cap.age = -10;
		cap.setName("김준수");
		cap.setAge(-10);
		
//		System.out.println("이름:" + cap.name);
//		System.out.println("나이:" + cap.age);
		System.out.println("이름:" + cap.getName());
		System.out.println("나이:" + cap.getAge());

	}

}
