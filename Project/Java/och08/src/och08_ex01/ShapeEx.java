package och08_ex01;

public class ShapeEx {

	//HW01
//	------선과 점으로 구성됐다.-------
//	삼..각형을 그린다
//	------선과 점으로 구성됐다.-------
//	사각형을 그린다
//	Rectangle 사각형 Display을 그린다
//	------선과 점으로 구성됐다.-------
//	원...을 그린다
	
//	//방법1
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Triangle triangle = new Triangle();
//		Rectangle rectangle = new Rectangle();
//		Circle circle = new Circle();
//		
//		triangle.print();
//		triangle.draw();
//		
//		rectangle.print();
//		rectangle.draw();
//		rectangle.display();
//		
//		circle.print();
//		circle.draw();
//	}
	
	//방법2
	static void Action(Shape v) {
		//instanceof : 객체 비교 연산자
		if(v instanceof Triangle)
		{
			((Triangle)v).print();
			((Triangle)v).draw();
		}
		else if(v instanceof Rectangle)
		{
			((Rectangle)v).print();
			((Rectangle)v).draw();
			((Rectangle)v).display();
		}
		else if(v instanceof Circle)
		{
			((Circle)v).print();
			((Circle)v).draw();
		}
		else
		{
			System.out.println("오류");
		}
	}
	
	public static void main(String[] args) {
		
		Shape[] s = new Shape[3];
		s[0] = new Triangle();
		s[1] = new Rectangle();
		s[2] = new Circle();
		
		
		for(Shape value : s)
		{
			Action(value);
		}
		
	}

}
