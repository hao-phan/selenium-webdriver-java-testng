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

import net.bytebuddy.asm.Advice.Argument;

public class Topic_08_Textbox_TextArea_P1 {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String emailAddress, loginUrl, userNameLogin, passwordLogin, customerName, gender;
	String dateOfBirthInput, dateOfBirthOutput, addressInput, addressOutput, city, state, pin, mobileNumber;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver(); //FirefoxDriver
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/");
		loginUrl = driver.getCurrentUrl();
		
		emailAddress = "johndeep" + generateRandomeNumber() + "@hotmail.net"; 
		customerName = "John Deep";
		gender = "male";
		dateOfBirthInput = "08/15/1960";
		dateOfBirthOutput = "1960-08-15";
		addressInput = "123 PO Box\nLos Angeles\nUnited State";
		addressOutput = "123 PO Box Los Angeles United State";
		city = "Hawaii";
		state = "Los Angeles";
		pin = "126589";
		mobileNumber = "0987566325";
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
		
		//Input
		driver.findElement(By.xpath("//ul[@class='menusubnav']//a[text()='New Customer']")).click();

		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@name='rad1' and @value='m']")).click();
		
		WebElement dateOfBirthTextBox = driver.findElement(By.xpath("//input[@name='dob']"));
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", dateOfBirthTextBox);
		sleepInSecond(3);
		dateOfBirthTextBox.sendKeys(dateOfBirthInput);
		
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(addressInput);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(mobileNumber);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordLogin);
		driver.findElement(By.xpath("//input[@name='sub']")).click();

		//Output
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//tr//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//tr//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//tr//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//tr//td[text()='Address']/following-sibling::td")).getText(), addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//tr//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//tr//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//tr//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//tr//td[text()='Mobile No.']/following-sibling::td")).getText(), mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//tr//td[text()='Email']/following-sibling::td")).getText(), emailAddress);
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