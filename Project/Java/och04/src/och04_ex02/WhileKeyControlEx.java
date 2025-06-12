package och04_ex02;

import java.io.IOException;

public class WhileKeyControlEx {

	public static void main(String[] args) throws IOException {
		boolean run = true;
		int speed = 0;
		int keycode = 0;
		
//      HW04  			
//		선택: 1
//		keyCode->49
//      결과 	1) speed + 1
//		    2) 보여줘	[ 현재 속도=" + speed ]
//		2
//		keyCode->50
//      결과 1) speed - 1		
//	        2) 보여줘	[ 현재 속도=" + speed ]

//		3
//		keyCode->51
//      결과  run = false
//           Escape
		
		while(run)
		{
			if(keycode != 13 && keycode != 10)
			{
				System.out.println("---------------------");
				System.out.println("1.증속 | 2.감속 | 3.중지");
				System.out.println("---------------------");
				System.out.print("선택 : ");
			}
			
			keycode = System.in.read();
//			System.out.println("keycode:" + keycode);
			
			switch(keycode)
			{
				case 49: speed++; System.out.println("[현재 속도=" + speed +"]");
					break;
				case 50: speed--; System.out.println("[현재 속도=" + speed +"]");
					break;
				case 51: run = false;
					break;
				default:
					break;
			}
		}
		
		System.out.println("PGM The End");

	}

}
