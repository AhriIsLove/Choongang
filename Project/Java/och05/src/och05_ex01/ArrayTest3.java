package och05_ex01;

public class ArrayTest3 {

	public static void main(String[] args) {
		 int array[] = {76, 45, 34, 89, 100, 50, 90, 92};
		 int sum = 0, avg = 0, max = 0, min = 100;
		 
		 for(int score : array)
		 {
			 //합계
			 sum += score;
			 
			 //max 구분
			 if(max < score) max = score;
			 
			 //min 구분
			 if(min > score) min = score;
		 }
		 
		 //평균
		 avg = sum / array.length;

		 System.out.println("합계 : " + sum);
		 System.out.println("평균 : " + avg);
		 System.out.println("최대 : " + max);
		 System.out.println("최소 : " + min);
	}

}
