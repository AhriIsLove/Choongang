package och09_ex01;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFor1 {

	public static void main(String[] args) {
		Date today = new Date();
		System.out.println("Date() : " + today);
		
		SimpleDateFormat[] simpleDateFormat = new SimpleDateFormat[9];
		
		simpleDateFormat[0] = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println("simpleDateFormat[0].format(Date) : " + simpleDateFormat[0].format(today));

		//(E)->요일
		simpleDateFormat[1] = new SimpleDateFormat("yy년 MM월 dd일 E요일");
		System.out.println("simpleDateFormat[1].format(Date) : " + simpleDateFormat[1].format(today));

		simpleDateFormat[2] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println("simpleDateFormat[2].format(Date) : " + simpleDateFormat[2].format(today));
		
		// (a)->오전/오후
		simpleDateFormat[3] = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
		System.out.println("simpleDateFormat[3].format(Date) : " + simpleDateFormat[3].format(today));
		
		System.out.println("------------------------------------------------------");
		simpleDateFormat[4] = new SimpleDateFormat("오늘은 이번 년의 D번째 날입니다.");
		simpleDateFormat[5] = new SimpleDateFormat("오늘은 이번 달의 d번째 날입니다.");
		simpleDateFormat[6] = new SimpleDateFormat("오늘은 이번 년의 w번째 주입니다.");
		simpleDateFormat[7] = new SimpleDateFormat("오늘은 이번 달의 W번째 주입니다.");
		simpleDateFormat[8] = new SimpleDateFormat("오늘은 이번 달의 F번째 E요일입니다.");

		System.out.println("simpleDateFormat[4].format(Date) : " + simpleDateFormat[4].format(today));
		System.out.println("simpleDateFormat[5].format(Date) : " + simpleDateFormat[5].format(today));
		System.out.println("simpleDateFormat[6].format(Date) : " + simpleDateFormat[6].format(today));
		System.out.println("simpleDateFormat[7].format(Date) : " + simpleDateFormat[7].format(today));
		System.out.println("simpleDateFormat[8].format(Date) : " + simpleDateFormat[8].format(today));
	}

}
