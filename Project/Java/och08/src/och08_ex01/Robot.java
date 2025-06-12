package och08_ex01;

//클래스와 클래스 연결 용도
public interface Robot {

}

class DanceRobot implements Robot{
	void Dance() {System.out.println("춤!");}
}
class DrawRobot implements Robot{
	void Draw() {System.out.println("그림!");}
}
class SingRobot implements Robot{
	void Sing() {System.out.println("노래!");}
}