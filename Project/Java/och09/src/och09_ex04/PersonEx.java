package och09_ex04;

public class PersonEx {

	public static void main(String[] args) {
		Person p1 = new Person(1000, "광해");
		Person p2 = new Person(1000, "광해군");
		
		// 문제 주민번호만 같으면 같다가 되도록
		if(p1.equals(p2)) System.out.println("같다");
		else System.out.println("다르다");
			
	}

}