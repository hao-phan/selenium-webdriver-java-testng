package javaTester;

public class Topic_05_Primitive_Casting {

	public static void main(String[] args) {
		//Kiểu Ngầm định không mất dữ liêu -> Từ nhỏ đến lớn
		byte bNumber = 126;
		System.out.println(bNumber);
		
		short sNumber = bNumber;
		System.out.println(sNumber);
		
		int iNumber = sNumber;
		System.out.println(iNumber);
		
		long lNumber = iNumber;
		System.out.println(lNumber);
		
		float fNumber = lNumber;
		System.out.println(fNumber);
		
		double dNumber = fNumber;
		System.out.println(dNumber);
	}

}
