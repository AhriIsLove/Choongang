package och09_ex01;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeCreateEx {

	public static void main(String[] args) {
		LocalDate currDate = LocalDate.now();
		System.out.println("현재 날짜 : " + currDate);
		LocalDate targetDate = LocalDate.of(2025, 05, 19);
		System.out.println("목표 날짜 : " + targetDate);
		
		System.out.println();
		
		LocalTime currTime = LocalTime.now();
		System.out.println("현재 시간 : " + currTime);
		LocalTime targetTime = LocalTime.of(19, 30, 10, 5);
		System.out.println("목표 시간 : " + targetTime);
	
		System.out.println();
		
		LocalDateTime currDateTime = LocalDateTime.now();
		System.out.println("현재 날짜시간 : " + currDateTime);
		LocalDateTime targetDateTime = LocalDateTime.of(2025, 11, 15, 17, 50, 30);
		System.out.println("목표 날짜시간 : " + targetDateTime);

		System.out.println();
		
		// 협정 세계시와 시간존(TimeZone) 
		// KST : 한국 표준 시간
        // UTC : 국제적인 표준 시간의 기준으로 쓰이는 시각
		ZonedDateTime utcDateTime1 = ZonedDateTime.now();
		System.out.println("국제표준시간1 : " + utcDateTime1);
		ZonedDateTime utcDateTime2 = ZonedDateTime.now(ZoneId.of("UTC"));
		System.out.println("국제표준시간2 : " + utcDateTime2);
		ZonedDateTime utcDateTime3 = ZonedDateTime.now(ZoneId.of("America/New_York"));
		System.out.println("국제표준시간3 : " + utcDateTime3);
		
		System.out.println();
		
		
	}

}
