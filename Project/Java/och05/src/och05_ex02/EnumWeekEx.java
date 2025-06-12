package och05_ex02;

import java.util.Calendar;

public class EnumWeekEx {

	public static void main(String[] args) {
		 WEEK today = null;
		 Calendar cal = Calendar.getInstance();
		 
		 int weekNum = cal.get(Calendar.DAY_OF_WEEK);
		 System.out.println("weekNum:" + weekNum);
		 
		 switch (weekNum) {
			 case 1:
				today = WEEK.SUNDAY;
				break;
			 case 2:
				today = WEEK.MONDAY;
				break;
			 case 3:
				today = WEEK.TUESDAY;
				break;
			 case 4:
				today = WEEK.WEDNESDAY;
				break;
			 case 5:
				today = WEEK.THURSDAY;
				break;
			 case 6:
				today = WEEK.FRIDAY;
				break;
			 case 7:
				today = WEEK.SATURDAY;
				break;
			default:
				break;
		}
		 
		 System.out.println("오늘 요일 : " + today);

		 if(today == WEEK.SUNDAY) System.out.println("일요일에는 놀아요");
		 else System.out.println("공부해요");
	}

}
