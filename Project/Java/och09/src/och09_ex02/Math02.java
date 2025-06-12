package och09_ex02;

public class Math02 {

	public static void main(String[] args) {
		double d1 = 4.156;
		double d2 = 4.67;
		double d3 = -4.15;
		double d4 = -4.67;
		
		//round : 반옹림
		System.out.println("round d1:" + Math.round(d1));
		System.out.println("round d2:" + Math.round(d2));
		System.out.println("round d3:" + Math.round(d3));
		System.out.println("round d4:" + Math.round(d4));
		
		System.out.println();

		// floor : 내림(자기보다 작거나같은 정수 중 가장 큰 정수)
		System.out.println("floor d1:" + Math.floor(d1));
		System.out.println("floor d2:" + Math.floor(d2));
		System.out.println("floor d3:" + Math.floor(d3));
		System.out.println("floor d4:" + Math.floor(d4));

		System.out.println();
		
		// ceil : 올림(자기보다 크거나같은 정수중 가장 작은 정수)
		System.out.println("ceil d1:" + Math.ceil(d1));
		System.out.println("ceil d2:" + Math.ceil(d2));
		System.out.println("ceil d3:" + Math.ceil(d3));
		System.out.println("ceil d4:" + Math.ceil(d4));

	}

}
