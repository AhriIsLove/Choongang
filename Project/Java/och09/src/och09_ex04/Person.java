package och09_ex04;

public class Person {
	int		id;
	String	name;
	
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
//	public boolean equals(Person obj) {
//		// TODO Auto-generated method stub
////		return super.equals(obj);
//		
//		if(this.id == obj.id) return true;
//		else return false;
//	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
//		return super.equals(obj);
		
		if(obj instanceof Person)
		{
			if(obj != null && id == ((Person) obj).id)
			{
				return true;
			}
			//obj가 null이면서 Person도 null 일경우도 처리해야 함
		}
		
		return false;
	}
}
