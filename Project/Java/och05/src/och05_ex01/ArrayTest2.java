package och05_ex01;

public class ArrayTest2 {

	public static void main(String[] args) {
		 int money = 2680;
		 
		 int[] unit = {500, 100, 50, 10};
		 
		 //각각의 동전이 최대한 나올 수 있는 Count
		 for(int i=0; i < unit.length; i++)
		 {
			 int count = money / unit[i];
			 
			 System.out.println(unit[i] + "원 짜리 동전 : " + count + "개");
			 
			 money%=unit[i];
		 }

	}

}
