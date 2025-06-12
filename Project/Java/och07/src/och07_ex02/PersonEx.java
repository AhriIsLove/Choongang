package och07_ex02;

public class PersonEx {

	public static void main(String[] args) {
		Student st1 = new Student();
		st1.name = "수지";
		st1.setAge(20);
		st1.setSno("S001");
		st1.PrintAll();
		
		Teacher tc1 = new Teacher();
		tc1.setName("양만춘");
		tc1.setAge(35);
		tc1.setSubject("JAVA");
		tc1.PrintAll();
		
		Manager mg1 = new Manager();
		mg1.setName("원빈");
		mg1.setAge(29);
		mg1.setPart("연기자");
		mg1.PrintAll();
	}

}