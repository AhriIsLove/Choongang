package och07;

//HW01
//재귀함수
class D1
{
	void hi (int cnt)
	{
		if(cnt >= 0)
		{
			System.out.println("안녕, 나야 !! cnt-->" + cnt);
			hi(--cnt);
		}
		else
		{
			
		}
	}
}

public class HiEx {

	public static void main(String[] args) {
		D1 d = new D1();
		d.hi(6);

	}

}
