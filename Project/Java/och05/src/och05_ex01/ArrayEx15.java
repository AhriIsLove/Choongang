package och05_ex01;

public class ArrayEx15 {

	public static void main(String[] args) {
		 if(args.length != 3)
		 {
			 System.out.println("usage: java ArrayEx15 NUM1 OP NUM2");
			 System.exit(0);
		 }

		 int num1 = Integer.parseInt(args[0]);
		 char op = args[1].charAt(0);
		 int num2 = Integer.parseInt(args[2]);
		 
		 int result = 0;
		 switch(op)
		 {
			 case '+':
				 result = num1 + num2;
				 break;
			 case '-':
				 result = num1 - num2;
				 break;
			 case 'x':// * 는 .classpath를 가르키기 때문에 x로 변경
				 result = num1 * num2;
				 break;
			 case '/':
				 result = num1 / num2;
				 break;
			 default:
				 System.out.println("가능한 연산자 : + - x /");
				 break;
		 }
		 
		 System.out.println("결과 : " + result);

	}

}
