package och07;

public class EncapsuleA {
	private String name;
	private int age;
	
	//겟셋 만들기
	//메뉴 -> Source -> Generate Getters and Setters...
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
		if(age <= 0) System.out.println("나이 입력 오류");
		else this.age = age;
	}
	
	
}
