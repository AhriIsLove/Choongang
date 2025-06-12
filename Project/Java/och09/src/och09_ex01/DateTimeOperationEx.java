package och09_ex01;

import java.time.LocalDateTime;

public class DateTimeOperationEx {

	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		LocalDateTime target = now
				.plusYears(1)
				.minusMonths(2)
				.plusDays(3)
				.plusHours(5)
				.minusMinutes(7)
				.plusSeconds(10);
		System.out.println(target);

	}

}
