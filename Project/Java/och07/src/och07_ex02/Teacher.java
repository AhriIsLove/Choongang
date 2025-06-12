package och07_ex02;

public class Teacher {
	private String name;
	private int age;
	private String subject;//과목
	
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void PrintAll() {
		System.out.printf("이름 : %s, 나이 : %d, 과목 : %s\n", name, age, subject);
	}

}
