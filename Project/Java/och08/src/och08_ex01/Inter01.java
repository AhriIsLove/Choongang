package och08_ex01;

import och08_ex01.Inter01;

//-------------------------------------------------------
//인터페이스는 사용목적
//  1) 양식의 역할( 큼) 함 --> 표준화  
//  2) 다중상속 지원(극히 일부분..)
//  3) class 와 class 를 연결 해줌 (중요)
//-------------------------------------------------------

public interface Inter01 {
	//interface의 변수는 final변수로 자동 판단
	int NUM1 = 3;
	
	//interface의 method는 추상 method
	void Display();
	void Print();	
}

class InterClass implements Inter01
{

	@Override
	public void Display() {
//		NUM1 = NUM1 + 3;//final 변수는 수정 불가능
		System.out.println("InterClass의 Display");
		
	}

	@Override
	public void Print() {
		// TODO Auto-generated method stub
		System.out.println("InterClass의 Print");
	}
	
}
