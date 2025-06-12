package och06;

class Subscribe {
	private String telnum;
	
	public String name;
	public int age;
	
	public Subscribe(String name, String telnum, int age) {
		this.name = name;
		this.telnum = telnum;
		this.age = age;
	}
	public Subscribe(String name) {
		this.name = name;
	}
	
	void ChangeTelnum(String telnum)
	{
		this.telnum = telnum;
	}
	void SetAge(int age)
	{
		if(age >= 20)
			this.age = age;
		else
			System.out.println("20살 이상만 등록 가능");
	}
	int GetAge()
	{
		return this.age;
	}
	void Print()
	{
		System.out.println("이름:" + name + "\t번호:" + telnum + "\t나이:" + age);
	}
}

public class SubscribeEx {

	public static void main(String[] args) {
		Subscribe s1 = new Subscribe("이성계", "02-1234-5678", 45);
		Subscribe s2 = new Subscribe("김유신");

		s2.SetAge(20);
		
		s1.Print();
		s2.Print();
		
	}

}
