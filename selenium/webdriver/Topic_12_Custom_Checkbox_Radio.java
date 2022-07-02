package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Custom_Checkbox_Radio {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExcutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver(); //FirefoxDriver
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		jsExcutor = (JavascriptExecutor) driver;
	}

	//@Test
	public void TC_01_Custom_Checkbox_Radio_Input() {
		// Có thẻ Input để click + verify
		driver.get("https://material.angular.io/components/checkbox/examples");
		// Hàm isSelected() chỉ work với loại element là checkbox/ radio (phải là thẻ input)
		
		// Case 1:
		// - Thẻ input ko click được -> Failed
		// - Thẻ input dùng để verify -> Passed
		// 		driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).click();
		
		// Case 2:
		// - Ko dùng thẻ input để click -> dùng thẻ span chứa text để click -> Passed
		// - Ko dùng thẻ input để verify -> Failed
		//		By checkBox = By.xpath("//span[text()='Checked']");
		//		driver.findElement(checkBox).click();
		//		sleepInSecond(1);
		//		Assert.assertTrue(driver.findElement(checkBox).isSelected());
		
		// Case 3: Thỏa mãn hết điều kiện vừa click + verify được
		// Nhưng gặp vấn đề là 1 element nhưng mà phải Define ra tới 2 cái Locator /By => Khi maintain code sẽ sinh ra nhiều phụ thuộc + nhiều dòng code
		// - Ko dùng thẻ input để click -> dùng thẻ span chứa text để click -> Passed
		// - Thẻ input dùng để verify -> Passed
		//		By checkBoxText = By.xpath("//span[text()='Checked']");
		//		driver.findElement(checkBoxText).click();
		//		sleepInSecond(1);
		//			
		//		By checkBox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		//		Assert.assertTrue(driver.findElement(checkBox).isSelected());
		
		// Case 4: Thỏa mãn hết điều kiện vừa click + verify được (Dùng thư viện JavascriptExcutor)
		// JavascriptExcutor: Ko quan tâm element bị che hay không (vẫn Click được)
		// - Dùng thẻ input để click -> dùng thẻ span chứa text để click -> Passed
		// - Thẻ input dùng để verify -> Passed
			By checkBox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
			jsExcutor.executeScript("arguments[0].click();", driver.findElement(checkBox));
			sleepInSecond(1);
			Assert.assertTrue(driver.findElement(checkBox).isSelected());
			
			By beforeRadio = By.xpath("//span[text()='Before']/preceding-sibling::span/input");
			jsExcutor.executeScript("arguments[0].click();", driver.findElement(beforeRadio));
			sleepInSecond(1);
			Assert.assertTrue(driver.findElement(beforeRadio).isSelected());
	}

	@Test
	public void TC_02_Custom_Checkbox_Radio_NoInput() {
		// KHÔNG có thẻ Input để click + verify
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		// Radio
		By hanoiRadio = By.xpath("//div[@aria-label='Hà Nội']");
		driver.findElement(hanoiRadio).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(hanoiRadio).getAttribute("aria-checked"), "true");
		
		// Checkbox
		By miquangCheckBox = By.xpath("//div[@aria-label='Mì Quảng']");
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(miquangCheckBox));
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(miquangCheckBox).getAttribute("aria-checked"), "true");
	}

	@Test
	public void TC_03() {
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