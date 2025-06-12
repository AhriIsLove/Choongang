package och09_ex01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

//그레고리력은 현재 전 세계적으로 통용하는 태양력으로, 1582년에 교황 그레고리오 13세가 
//이전 율리우스력을 개정하여 시행한 역법을 말한다. 율리우스력 계산법은 천체운행과 불일치함으로 
//오차가 발생했기에 이를 보정하기 위해서 새로운 역법
public class GregorianCalendar01 {

	public static void main(String[] args) {
		GregorianCalendar gc = new GregorianCalendar();
		int	year	= gc.get(GregorianCalendar.YEAR);
		int	month	= gc.get(GregorianCalendar.MONTH) + 1;
		int	day		= gc.get(GregorianCalendar.DATE);
		
		System.out.printf("오늘은 %d년 %d월 %d일 입니다.\n", year, month, day);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd(E) (a)hh:mm:ss");
		Date date = gc.getTime();
		System.out.println(sdf.format(date));
	}

}
