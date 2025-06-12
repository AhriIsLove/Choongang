package och10_ex05;

//최소 임금 Error Msg
public class MyException0202 extends Exception {
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
//		return super.getMessage();
		return "사장님 5000만원 넘 많아요 , 좀 적당히 합시다";
	}
}
