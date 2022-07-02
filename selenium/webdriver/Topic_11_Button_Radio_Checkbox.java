package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Button_Radio_Checkbox {
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
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
		
		//Verify button is Disabled
		By loginButtonBy = By.xpath("//button[@class='fhs-btn-login']");
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());

		driver.findElement(By.id("login_username")).sendKeys("0973800764");
		driver.findElement(By.id("login_password")).sendKeys("Bull@123");
		sleepInSecond(2);
		
		//Verify button is Enabled
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		
		//Check color khi button đã Enable
		String loginButtonEnable = Color.fromString(driver.findElement(loginButtonBy).getCssValue("background-color")).asHex().toUpperCase(); //Lấy được color là rbg sau đó dùng hàm asHex để đổi qua #c92127 xong upperCase lên để check
		Assert.assertEquals(loginButtonEnable, "#C92127");
	}

	@Test
	public void TC_02_Default_Radio_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		By emotionalCheckbox = By.id("input_52_6");
		By digestiveCheckbox = By.id("input_52_14");
		By venerealCheckbox = By.id("input_52_24");

		By fiveDayRadio = By.id("input_80_3");
		By dietPlanRadio = By.id("input_81_2");
		By glassesRadio = By.id("input_76_2");
		
		// 1 - Chọn (Click - Selected)
		// Checkbox
		driver.findElement(emotionalCheckbox).click(); // Emotional Disorder
		driver.findElement(digestiveCheckbox).click(); // Digestive Problems
		driver.findElement(venerealCheckbox).click(); // Venereal Disease
		sleepInSecond(1);
		
		// Raido
		driver.findElement(fiveDayRadio).click(); // 5+ days
		driver.findElement(dietPlanRadio).click(); // I don't have a diet plan
		driver.findElement(glassesRadio).click(); // 3-4 glasses/day
		sleepInSecond(1);
		
		// 2 - Verify (isSelected)
		Assert.assertTrue(driver.findElement(emotionalCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(digestiveCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(venerealCheckbox).isSelected());

		Assert.assertTrue(driver.findElement(fiveDayRadio).isSelected());
		Assert.assertTrue(driver.findElement(dietPlanRadio).isSelected());
		Assert.assertTrue(driver.findElement(glassesRadio).isSelected());
		
		// 3 - Deselected
		// Checkbox -> Click tiếp sẽ là deselected
		driver.findElement(emotionalCheckbox).click();
		driver.findElement(digestiveCheckbox).click();
		driver.findElement(venerealCheckbox).click();
		sleepInSecond(1);
		
		// Raido -> CLick nữa thì Radio vẫn là chọn
		driver.findElement(fiveDayRadio).click();
		driver.findElement(dietPlanRadio).click();
		driver.findElement(glassesRadio).click();
		sleepInSecond(1);
		
		// 4 - Verify (Deselected)
		Assert.assertFalse(driver.findElement(emotionalCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(digestiveCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(venerealCheckbox).isSelected());
		
		Assert.assertTrue(driver.findElement(fiveDayRadio).isSelected());
		Assert.assertTrue(driver.findElement(dietPlanRadio).isSelected());
		Assert.assertTrue(driver.findElement(glassesRadio).isSelected());
	}

	@Test
	public void TC_03_SelectAllCheckbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> selectAll = driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		// Chạy for để duyệt và click chọn từng cái
		for (WebElement checkbox : selectAll) {
			if (!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
		}
		
		// Verify all checkboxes
		for (WebElement checkbox : selectAll) {
			Assert.assertTrue(checkbox.isSelected());
		}
		
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