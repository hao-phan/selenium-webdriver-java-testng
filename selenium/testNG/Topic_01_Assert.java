package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_01_Assert {
	WebDriver driver;
	
	@Test
	public void TC_01() {
		// 3 hàm Assert hay dùng
		// Kiểm tra tính đúng đắn dữ liệu

		// 1 - Kiểm tra dữ liệu mình mong muốn là ĐÚNG
		// Email Text Box hiển thị
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
		
		// 2 - Kiểm tra dữ liệu mình mong muốn là SAI
		// Email Text Box KHÔNG hiển thị
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
		
		// 3 - Kiểm tra dữ liệu mình mong muốn với dữ liệu thực tế BẰNG NHAU
		Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"), "Search entire store here...");
	}
}
