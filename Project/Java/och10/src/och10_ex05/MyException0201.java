package och10_ex05;

//최소 임금 Error Msg
public class MyException0201 extends Exception {
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
//		return super.getMessage();
		return "사장님 200만원보다 적어요!! 이게 뭡니까?";
	}
}
