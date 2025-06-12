package och05_ex01;

public class ArrayTest5 {

	public static void main(String[] args) {
		 String[] arrStrColumn = {"이름", "국어", "영어", "수학", "총점", "평균"};
		 String[] arrStrPeople = {"김준수", "조정은", "안예은", "전동석"};
		 
		 int[][] score = {
				 {80,70,90},
				 {70,90,90},
				 {80,70,80},
				 {90,90,70}};
		 
		 int[] tot = new int[3];
		 int totSum = 0, totAvg = 0;
		 int sum = 0, avg = 0;
		 
		 System.out.println("성적표");
		 
		 //칼럼전시
		 for(String col : arrStrColumn)
			 System.out.print(col + "\t");
		 
		 //기준선
		 System.out.println("\n---------------------------------------------");
		 
		 //
		 for(int i=0; i<score.length; i++)
		 {
			 //이름
			 System.out.print(arrStrPeople[i]+ "\t");
			 
			 //국영수 점수
			 for(int j=0; j<score[i].length; j++)
			 {
				 System.out.print(score[i][j] + "\t");
				 sum += score[i][j];
				 tot[j] += score[i][j];
			 }
			 
			 //총점
			 System.out.print(sum + "\t");
			 
			 //평균
			 avg = sum/score[i].length;
			 System.out.print(avg + "\t");
			 
			 //초기화
			 sum = 0;
			 //줄바꿈
			 System.out.println();
		 }

		 //기준선
		 System.out.println("---------------------------------------------");

		 //과목별 총계
		 System.out.print("총계\t");
		 for(int i=0; i<tot.length; i++)
		 {
			 System.out.print(tot[i] + "\t");
			 
			 totSum += tot[i]; 
		 }

		 totAvg = totSum/(score.length*score[0].length);
		 System.out.print(totSum + "\t" + totAvg);

	}

}
