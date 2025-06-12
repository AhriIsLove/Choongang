package och07_ex01;

class AddShow{
	int Add(int a, int b)
	{
		return a + b;
	}
	String Print(String a)
	{
		return a;
	}
}

public class Return02 {

	public static void main(String[] args) {
		AddShow as = new AddShow();
		int kk = as.Add(7,6);
		System.out.println("연산결과 : " + kk);
		String result = as.Print("대조영");
		System.out.println("result : " + result);
	}

}
