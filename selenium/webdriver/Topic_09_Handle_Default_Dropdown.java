package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Handle_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	String firstName, lastName, day, month, year, emailAddress, companyName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver(); //FirefoxDriver
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstName = "Hao";
		lastName = "Phan";
		day = "14";
		month = "March";
		year = "1995";
		emailAddress = "hao" + generateRandomeNumber() + "@hotmail.net";
		companyName = "G1";
	}

	@Test
	public void TC_01_Default_Dropdown() {
//		<select name="DateOfBirthDay" class="valid" aria-invalid="false">
	//		<option value="0">Day</option>
	//		<option value="1">1</option>
	//		<option value="2">2</option>
	//		<option value="3">3</option>
	//		<option value="4">4</option>
	//		<option value="5">5</option>
//		</select>
//
		////Index: thứ tự của thẻ option
		//select.selectByIndex(1);
		//select.selectByIndex(6);
		//
		////Value: Giá trị của thuộc tính value
		//select.selectByValue("0");
		//select.selectByValue("5");
		//
		////Text: Item text
		//select.selectByVisibleText("Day");
		//select.selectByVisibleText("5");
		
		//Input
		driver.get("https://demo.nopcommerce.com/register");

		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		//Khởi tạo Day
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))); //Khởi tạo cho thèn nào thì chỉ thèn đó được sử dụng
		select.selectByVisibleText(day); //Chọn
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day); //Kiểm tra có chọn đúng số 14 hay không
		
		//Khởi tạo Month
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		//Khởi tạo Year
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);

		driver.findElement(By.id("Password")).sendKeys("Bull@123");

		driver.findElement(By.id("ConfirmPassword")).sendKeys("Bull@123");
		driver.findElement(By.id("register-button")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();

		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		//Khởi tạo Month
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		//Khởi tạo Year
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond (long timeInsecond){
		try {
			Thread.sleep(timeInsecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int generateRandomeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}