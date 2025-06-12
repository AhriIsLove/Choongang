package och07_ex02;

public class Manager {
	private String name;
	private int age;
	private String part;//담당
	
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

	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}

	public void PrintAll() {
		System.out.printf("이름 : %s, 나이 : %d, 담당 : %s\n", name, age, part);
	}
}
