package och08_ex01;

public abstract class Shape {
	int a = 3;
	abstract void draw();
	void print() {
		System.out.println("---------선과 점으로 구성되었다---------");
	}
	
}

class Rectangle extends Shape{

	@Override
	void draw() {
		// TODO Auto-generated method stub
		System.out.println("사각형을 그린다");
	}
	void display() {
		System.out.println("Rectangle.Display");
	}
}
class Triangle extends Shape{

	@Override
	void draw() {
		// TODO Auto-generated method stub
		System.out.println("삼..각형을 그린다");
	}
	
}
class Circle extends Shape{

	@Override
	void draw() {
		// TODO Auto-generated method stub
		System.out.println("원...을 그린다");
	}
	
}