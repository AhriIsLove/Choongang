package och10_ex04;

public class MyException extends Exception {
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
//		return super.getMessage();
		String errMsg;
		
		errMsg = "Logic Error : 10보다 큼";
		
		return errMsg;
	}
}
