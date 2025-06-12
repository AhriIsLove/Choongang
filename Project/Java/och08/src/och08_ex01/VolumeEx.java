package och08_ex01;

public class VolumeEx {

	static void Action(Volume v) {
		//instanceof : 객체 비교 연산자
		if(v instanceof Tv)
		{
			Tv tv = (Tv)v;
			tv.volumeUp();
			tv.volumeDown();
			tv.play();
		}
		else if(v instanceof Speaker)
		{
			((Speaker)v).volumeUp();
			((Speaker)v).volumeDown();
		}
		else if(v instanceof Audio)
		{
			((Audio)v).volumeUp();
			((Audio)v).volumeDown();
		}
		else
		{
			System.out.println("오류");
		}
	}
	
	//HW02
//	TV Volume 올리기
//	TV Volume 내리기
//	TV 화면을 본다
//	Speaker Volume 올리기
//	Speaker Volume 내리기
//	Audio Volume 올리기
//	Audio Volume 내리기
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Volume[] v = new Volume[3];
		v[0] = new Tv();
		v[1] = new Speaker();
		v[2] = new Audio();
		
		
		for(Volume value : v)
		{
			Action(value);
		}
	}

}
