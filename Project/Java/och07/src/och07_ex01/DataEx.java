package och07_ex01;

//Call By Value && Call By Reference ---> ***
class Data
{
	int kor, eng, math;
	
	//Call By Reference
	void addRef(Data d1, Data d2)
	{
		d1.kor = d2.eng;
		System.out.println("Method 국어 점수 : " + d1.kor);
	}
	//Call By Value
	int addKor(int kor)
	{
		kor += 10;
		System.out.println("kor=>" + kor);
		return kor;
	}
}

public class DataEx {

	public static void main(String[] args) {
		Data d1 = new Data();
		Data d2 = new Data();
		Data d3 = new Data();
		d1.kor = 50;
		d1.math = 70;
		d2.eng = 80;

		//Call By Reference
		d3.addRef(d1, d2);
		System.out.println("main 국어 점수 : " + d1.kor);
		
		System.out.println();
		
		System.out.println("d3.kor->" + d3.kor);
		System.out.println("d3.math->" + d3.math);
		System.out.println("d3.eng->" + d3.eng);
		
		System.out.println();
		
		//Call By Value		
		int d3_kor = d3.addKor(10);
		System.out.println("d3_kor->" + d3_kor);
		System.out.println("d3.kor->" + d3.kor);
		
		int[] a = {};
		System.out.println(a[0]);
		
	}

}
