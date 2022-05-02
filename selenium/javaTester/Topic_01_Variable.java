package javaTester;

public class Topic_01_Variable {
	
	//Global Variable

		static int number; //Biến Global nếu không khởi tạo dữ liệu nó sẽ lấy default //Dùng static vì hàm void bên dưới là staic
		static String address1 = "HCM"; //1
		String address2 = "HN";//2
		
		public static void main(String[] args) { 
			//Vì hàm void này dùng static nên nếu muốn sử dụng Global thì Biến global cũng phải là static
			//Local Variable
			
			int studentNumber = 99; //Biến local thì phải khởi tạo dữ liệu
			System.out.println(studentNumber);

			System.out.println(number);
			
			System.out.println(address1);//1
			
			Topic_01_Variable topic = new Topic_01_Variable();//2
			System.out.println(topic.address2);
			
		}

}
