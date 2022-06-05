package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Textbox_TextArea {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String emailAddress, loginUrl, userNameLogin, passwordLogin;
	String customerName, gender, dateOfBirth, address, city, state, pin, mobileNumber;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver(); //FirefoxDriver
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/");
		loginUrl = driver.getCurrentUrl();
		
		emailAddress = "hao" + generateRandomeNumber() + "@hotmail.net"; 
		customerName = "Hao Phan";
		gender = "male";
		dateOfBirth = "03/14/1995";
		address = "27/15 XTT";
		city = "HCM";
		state = "VN";
		pin = "123456789";
		mobileNumber = "0909888777";
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		userNameLogin = driver.findElement(By.xpath("//td[@class='accpage' and text()='User ID :']/following-sibling::td")).getText();
		
		passwordLogin = driver.findElement(By.xpath("//td[@class='accpage' and text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() {
		driver.get(loginUrl);
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userNameLogin);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordLogin);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		//Verify homepage dispayed
		driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText(), "Manger Id : " + userNameLogin);
	}

	@Test
	public void TC_03_Create_New_Customer() {
		driver.findElement(By.xpath("//ul[@class='menusubnav']//a[text()='New Customer']")).click();

		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@name='rad1' and @value='m']")).click();
		jsExecutor.executeScript(address, null);
		driver.findElement(By.xpath("//input[@name='dob']")).click();
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(mobileNumber);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordLogin);
		
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