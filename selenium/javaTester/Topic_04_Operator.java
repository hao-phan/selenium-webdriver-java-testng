package javaTester;

public class Topic_04_Operator {

	public static void main(String[] args) {
		int number = 10;
		number += 5; //number = number + 5 = 15
		
		System.out.println("number = " + number);
		
		// 15 / 5 = 3
		System.out.println("number = " + (number / 5));
		
		// 15 / 7 = 2 dư 1 (lấy dư)
		System.out.println("number = " + (number % 7));
		
		int so = 2;
		System.out.println("so = " + so++); //In ra so = 2 trước rồi mới cộng lên = 3
		
		System.out.println("so = " + ++so); //Cộng ra = 3 trước rồi mới in
		
		for (int i = 0; i < 3; i++) {
			System.out.println("i = " + i);
			//1. Check i = 0, i < 3 in i = 0 ra rồi ++ lên = 1
			//2. Check i = 1, i < 3 in i = 1 ra rồi ++ lên = 2
			//3. Check i = 2, i < 3 in i = 2 ra rồi ++ lên = 3
			//4. Check i = 3, i < 3 sai vì lúc này i = 3 mà  < 3 là sai nên ngừng vòng lặp for
		}
	}

}
