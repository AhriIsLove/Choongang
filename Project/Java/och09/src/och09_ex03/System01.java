package och09_ex03;

public class System01 {

	public static void main(String[] args) {
		String path = System.getenv("path");//환경변수-시스템변수(path) 가져오기
		String java_home = System.getenv("JAVA_HOME");
		
		System.out.println("path:" + path);
		System.out.println("java_home:" + java_home);

	}

}
