package javaTester;

public class Topic_05_Reference_Casting {

	public String studentName;
	
	public String getstudentName() {
		return studentName;
	}
	
	public void setstudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public void showStudentName() {
		System.out.println("Student name: " + studentName);
	}
	
	public static void main(String[] args) {
		Topic_05_Reference_Casting firstStudent = new Topic_05_Reference_Casting();
		Topic_05_Reference_Casting secondStudent = new Topic_05_Reference_Casting();
		
		firstStudent.setstudentName("Phan Tran Hao");
		secondStudent.setstudentName("Phan Hien Hao");
		
		firstStudent.showStudentName();
		secondStudent.showStudentName();
		
		//Ép kiểu
		// Cho first = second
		firstStudent = secondStudent;
		
		firstStudent.showStudentName();
		secondStudent.showStudentName();
		
		//Gán second = tên khác
		secondStudent.setstudentName("Hao Hao");
		
		firstStudent.showStudentName();
		secondStudent.showStudentName();
		
	}

}
