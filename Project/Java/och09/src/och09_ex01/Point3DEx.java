package och09_ex01;

class Point3D{
	int x, y, z;
	
	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//equals override(매개변수를 바꿔서 정확히는 오버라이드가 아니긴 함)
	public boolean equals(Point3D p_point3D) {
		// TODO Auto-generated method stub
//		return super.equals(obj);
		
		boolean result = false;
		
		if (x == p_point3D.x && y == p_point3D.y /* && z == p_point3D.z */) result = true;
		
		return result;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
//		return super.toString();//해시코드
		String result = "";
		
		result = "[x="+x+",y="+y+",z="+z+"]";
		
		return result;
	}
}

public class Point3DEx {

	public static void main(String[] args) {
		Point3D point1 = new Point3D(1, 2, 3);
		Point3D point2 = new Point3D(1, 2, 4);
		Point3D point3 = new Point3D(1, 2, 5);

		if(point1.equals(point2))
			System.out.println("point1.equals(point2) 같다");
		else 
			System.out.println("point1.equals(point2) 다르다");

		if(point1 == point2)
			System.out.println("point1 == point2 같다");
		else 
			System.out.println("point1 == point2 다르다");

		//println(String) -> 객체.toString();
		System.out.println(point1);
		System.out.println(point3);
		
	}

}
