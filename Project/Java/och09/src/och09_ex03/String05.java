package och09_ex03;

public class String05 {

	public static void main(String[] args) {
		String fullName = "Hello.java";

		int index1 = fullName.indexOf('.');
		System.out.println("index1:" + index1);
		int index2 = fullName.indexOf('j');
		System.out.println("index2:" + index2);
		
		//폴더 경로 찾을려면 lastIndexOf
		System.out.println("index3:" + fullName.lastIndexOf('a'));
		
		
		//파일명 확장자명 찾기
		String fileName = fullName.substring(0,index1);
		System.out.println("fileName:" + fileName);
		String extName = fullName.substring(index1 + 1);//index1 + 1부터 끝까지
		System.out.println("extName:" + extName);
	}

}
