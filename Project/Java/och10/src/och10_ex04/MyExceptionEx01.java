package och10_ex04;

public class MyExceptionEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int kkk = 15;
		
		try {
			if(kkk > 10) throw new MyException();
		} catch (MyException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
