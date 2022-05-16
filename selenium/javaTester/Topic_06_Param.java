package javaTester;

import org.testng.annotations.Test;

public class Topic_06_Param {
	
	// Hàm ko có tham số
	public void clickToElement() {
		
	}
	
	// Hàm có 1 tham số
	public void clickToElement(String elementName) {
		
	}
	
	// Hàm có 2 hoặc nhiều hơn các tham số
	public void clickToElement(String elementName, String elementLocator) {
		
	}

	@Test // Test method/ Test case/ Test script
	public void TC_01_getElement() {
		clickToElement("Textbox");
	}
	
}
