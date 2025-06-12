package och03_ex02;

//HW01
//학생 한 명이 가지는 연필 수->17
//남은 연필 수->24

public class Ex0203 {

	public static void main(String[] args) {
		int iPencils = 534;
		int iStudents = 30;

		//학생 한 명이 가지는 연필 수 --> pencilsPerStudent
		//남은 연필 수 --> pencilsLeft
		int pencilsPerStudent, pencilsLeft;

		pencilsPerStudent = iPencils / iStudents;
		pencilsLeft = iPencils % iStudents;

		System.out.println("학생 한 명이 가지는 연필 수->" + pencilsPerStudent);
		System.out.println("남은 연필 수->" + pencilsLeft);
	}

}
