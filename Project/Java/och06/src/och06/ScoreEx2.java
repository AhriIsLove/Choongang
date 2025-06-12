package och06;

class Score2 {
	/* 싱글톤 사용법
	private static Score2 singleton = new Score2();
	
	private Score2() {}
	
	static Score2 getInstance() {
		return singleton;
	}
	*/
	
	int kor, eng, math;
	
	public Score2(int kor, int eng, int math) {
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	void Print() {
		int sum = kor + eng + math;
		System.out.println("국어:" + kor +"\t영어:" + eng + "\t수학:" + math + "\t총점:" + sum + "\t평균:" + sum/3);
	}
}

public class ScoreEx2 {

	public static void main(String[] args) {
		Score2 sc1 = new Score2(78, 88, 97);
		Score2 sc2 = new Score2(88, 98, 96);
		
		sc1.Print();
		sc2.Print();

	}

}
