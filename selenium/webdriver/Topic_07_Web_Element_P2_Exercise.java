package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element_P2_Exercise {
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
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Displayed
		WebElement emailTextBox = driver.findElement(By.id("email"));
		Assert.assertTrue(emailTextBox.isDisplayed());
		/*if (emailTextBox.isDisplayed()) {
			emailTextBox.sendKeys("Automation Testing");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}*/
		
		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		Assert.assertTrue(ageUnder18Radio.isDisplayed());
		
		WebElement educationTextArea = driver.findElement(By.id("edu"));
		Assert.assertTrue(educationTextArea.isDisplayed());
		
		//NOT Displayed
		WebElement nameUser5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		Assert.assertFalse(nameUser5.isDisplayed());
	}

	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Enable
		WebElement emailTextBox = driver.findElement(By.id("email"));
		Assert.assertTrue(emailTextBox.isEnabled());
		/*if (emailTextBox.isEnabled()) {
			emailTextBox.sendKeys("Automation Testing");
			System.out.println("Email textbox is Enable");
		} else {
			System.out.println("Email textbox is Disable");
		}*/
		
		WebElement ageUnder18 = driver.findElement(By.id("under_18"));
		Assert.assertTrue(ageUnder18.isEnabled());
		
		WebElement educationTextArea = driver.findElement(By.id("edu"));
		Assert.assertTrue(educationTextArea.isEnabled());
		
		WebElement job1Enable = driver.findElement(By.id("job1"));
		Assert.assertTrue(job1Enable.isEnabled());
		
		WebElement job2Enable = driver.findElement(By.id("job2"));
		Assert.assertTrue(job2Enable.isEnabled());
		
		WebElement developmentCheckbox = driver.findElement(By.id("development"));
		Assert.assertTrue(developmentCheckbox.isEnabled());
		
		WebElement slider1 = driver.findElement(By.id("slider-1"));
		Assert.assertTrue(slider1.isEnabled());
		
		//Disable
		WebElement disablePassword = driver.findElement(By.id("disable_password"));
		Assert.assertFalse(disablePassword.isEnabled());
		/*if (disablePassword.isEnabled()) {
			emailTextBox.click();
			System.out.println("disablePassword is Enable");
		} else {
			System.out.println("disablePassword is Disable");
		}*/
	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement ageUnder18 = driver.findElement(By.id("under_18"));
		WebElement javaCheckBox = driver.findElement(By.id("java"));
		
		ageUnder18.click();
		javaCheckBox.click();

		Assert.assertTrue(ageUnder18.isSelected());
		Assert.assertTrue(javaCheckBox.isSelected());
		
		ageUnder18.click();
		javaCheckBox.click();
		
		Assert.assertTrue(ageUnder18.isSelected()); //Vẫn là True vì là Radio button
		Assert.assertFalse(javaCheckBox.isSelected()); //False vì đây là Checkbox
	}
	
	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		sleepInSecond(2);
		
		driver.findElement(By.id("email")).sendKeys("hao@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("Hao Phan");
		
		WebElement password = driver.findElement(By.id("new_password"));
		password.sendKeys("hao");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']/span")).isDisplayed());
		
		driver.findElement(By.id("marketing_newsletter")).click();
		Assert.assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());
		
		Assert.assertTrue(driver.findElement(By.id("create-account-enabled")).isEnabled());
		
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
}