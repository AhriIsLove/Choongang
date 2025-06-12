package och07_ex02;

public class Student {
	public String name;
	private int age;
	private String sno;//학번

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}

	public void PrintAll() {
		System.out.printf("이름 : %s, 나이 : %d, 학번 : %s\n", name, age, sno);
	}

}
