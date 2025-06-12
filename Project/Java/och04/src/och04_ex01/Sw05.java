package och04_ex01;

public class Sw05 {

	public static void main(String[] args) {
		int iNum = Integer.parseInt(args[0]);
		String strSeason = "";
		
		switch (iNum) {
			case 12:
			case 1:
			case 2:
				switch (iNum) {
				case 12: System.out.println("초겨울"); break;
				case 1: System.out.println("젤추워"); break;
				case 2: System.out.println("추워"); break;
				default:
					break;
				}
				strSeason = "겨울";
				break;
			case 3:
			case 4:
			case 5:
				if(iNum == 3 || iNum == 4) System.out.println("대박");
				else System.out.println("...");
				strSeason = "봄";
				break;
			case 6:
			case 7:
			case 8:
				strSeason = "여름";
				break;
			case 9:
			case 10:
			case 11:
				strSeason = "가을";
				break;
			default:
				strSeason = "오류";
				break;
		}
		
		System.out.println(iNum + "월은 " + strSeason + "입니다");
		
	}

}
