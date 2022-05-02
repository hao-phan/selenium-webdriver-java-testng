package javaTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Topic_02_Data_Type {
	// Primitive type/ value type: Nguyên thủy
	byte bNumber = 6;
	
	short sNumber = 15000;
	
	int iNumber = 65000;
	
	long lNumber = 65000;
	
	float fNumber = 15.98f;
	
	double dNumber = 15.98d;
	
	char cNumber = 1;
	
	boolean bStatus = false;
	
	// Reference type: Tham chiếu
	
	// String
	String address = "HCM";
	Integer number = 1;
	
	// Array
	String[] studentsAddress = {address, "HN", "DN"};
	Integer[] studentsNumber = {number, 2, 3, 4, 5};
	
	//Class
	Topic_02_Data_Type topic;
	
	//Interface
	WebDriver driver;
	
	//Object
	Object aOject;
	
	//Collection
		//List/ Set/ Queue/ Map
		List<WebElement> homePageLinks = driver.findElements(By.tagName("a"));
		Set<String> allWindows = driver.getWindowHandles();
		List<String> productName = new ArrayList<String>();

	public static void main(String[] args) {
		
	}

}
