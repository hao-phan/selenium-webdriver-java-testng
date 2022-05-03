package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver(); //FirefoxDriver
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Click đăng kí button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Kiểm tra message lỗi ở các field bắt buộc
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
		
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Nhập liệu - Input data
		driver.findElement(By.id("txtFirstname")).sendKeys("Phan Trần Hảo");
		driver.findElement(By.id("txtEmail")).sendKeys("haopt195@gmail@com");
		driver.findElement(By.id("txtCEmail")).sendKeys("haopt195@gmail@com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0909111222");
		
		//Click vào nút đăng kí
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Kiểm tra lỗi của Email và Confirm Email
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_03_Register_Incorrect_Confirm_Email() {
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Nhập liệu - Input data
		driver.findElement(By.id("txtFirstname")).sendKeys("Phan Trần Hảo");
		driver.findElement(By.id("txtEmail")).sendKeys("haopt195@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("haopt195@gmail@com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0909111222");
		
		//Click đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Kiểm tra lỗi của Confirm Email
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}
	
	@Test
	public void TC_04_Register_Password_Less_Than_6_Characters() {
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Nhập liệu - Input data
		driver.findElement(By.id("txtFirstname")).sendKeys("Phan Trần Hảo");
		driver.findElement(By.id("txtEmail")).sendKeys("haopt195@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("haopt195@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345");
		driver.findElement(By.id("txtPhone")).sendKeys("0909111222");
		
		//Click đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Kiểm tra lỗi của Password và Confirm Password
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_05_Register_Incorrect_Confirm_Password() {
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Nhập liệu - Input data
		driver.findElement(By.id("txtFirstname")).sendKeys("Phan Trần Hảo");
		driver.findElement(By.id("txtEmail")).sendKeys("haopt195@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("haopt195@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123457");
		driver.findElement(By.id("txtPhone")).sendKeys("0909111222");
		
		//Click đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Kiểm tra lỗi của Confirm Password
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_06_Register_Invalid_Phone_Number() {
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Nhập liệu - Input data
		driver.findElement(By.id("txtFirstname")).sendKeys("Phan Trần Hảo");
		driver.findElement(By.id("txtEmail")).sendKeys("haopt195@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("haopt195@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("090911122");
		
		//Click đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Kiểm tra lỗi của Số điện thoại phải từ 10-11 số.
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		//Clear data ở field Phone
		driver.findElement(By.id("txtPhone")).clear();
		
		//Nhập liệu lại field Phone
		driver.findElement(By.id("txtPhone")).sendKeys("090911122223");
		
		//Click đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
				
		//Kiểm tra lỗi của Số điện thoại phải từ 10-11 số.
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		//Clear data ở field Phone
		driver.findElement(By.id("txtPhone")).clear();
				
		//Nhập liệu lại field Phone
		driver.findElement(By.id("txtPhone")).sendKeys("1234567890");
				
		//Click đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
				
		//Kiểm tra lỗi của Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}