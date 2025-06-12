package och05_ex01;

public class ArrayTest4 {

	public static void main(String[] args) {
		int array[] = {76, 45, 34, 89, 100, 50, 90, 92};
		int temp = 0;

		System.out.println("정렬 전");
		for(int value : array)
		{
			System.out.print(value + "\t");
		}
		
		System.out.println("");

		//버블 정렬 방식
		for(int i=0; i < array.length; i++)
		{
			for(int j = i + 1;j < array.length; j++)
			{
				if(array[i] > array[j])
				{
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
				 
			}
		}
		
		System.out.println("정렬 후");
		for(int value : array)
		{
			System.out.print(value + "\t");
		}

	}

}
