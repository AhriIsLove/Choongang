package och09_ex01;

import java.util.Calendar;
import java.util.Date;

public class Calendar01 {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		/*
		 * 미국인의 개념으로 보면, 
		 * Month는 '1월,2월' 이런 순서가 있는 값이 아니고, 
		 * '개똥이달, 순동이달, 명자달' 이런 식으로 개별 명칭이 있는 대상입니다. 
		 * 요일도 마찬가지입니다. 
		 * 따라서, 숫자로 표현될 수 있는 대상이 아닌 것이죠. 
		 * 굳이 매핑을 하다 보니, 
		 * 전형적인 어레이 개념에서, [개똥이달, 순동이달, 명자달...] 로 매핑 되었구요. 
		 * 어레이 인자는 당연히 '0' 부터 시작하잖아요.
		 */
		int date = calendar.get(Calendar.DATE);
		
		System.out.printf("오늘은 %d년 %d월 %d일 입니다.\n", year, month, date);

		int hour = calendar.get(Calendar.HOUR);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		
		System.out.printf("지금은 %d시 %d분 %d초 입니다.\n", hour, min, sec);
		
		Date dt = calendar.getTime();
		System.out.println("Calendar.getTime() : " + dt);
		
		int dayOf1 = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("Calendar.DAY_OF_MONTH : " + dayOf1);//오늘은 이번달에 X번째 날
		int dayOf2 = calendar.get(Calendar.DAY_OF_YEAR);
		System.out.println("Calendar.DAY_OF_YEAR : " + dayOf2);//오늘은 올해에 X번째 날
	}

}
