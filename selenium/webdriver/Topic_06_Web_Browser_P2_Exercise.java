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

public class Topic_06_Web_Browser_P2_Exercise {
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
	public void TC_01_Verify_getCurrentUrl() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		sleepInSecond(1);
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']/span/span")).click();
		sleepInSecond(1);
		String createAnAccountUrl = driver.getCurrentUrl();
		Assert.assertEquals(createAnAccountUrl, "http://live.techpanda.org/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Verify_getTitle() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		sleepInSecond(1);
		String titleLoginPage = driver.getTitle();
		Assert.assertEquals(titleLoginPage, "Customer Login");
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']/span/span")).click();
		sleepInSecond(1);
		String titleCreateAccountPage = driver.getTitle();
		Assert.assertEquals(titleCreateAccountPage, "Create New Customer Account");
	}

	@Test
	public void TC_03_Verify_Navigation() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		sleepInSecond(1);
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']/span/span")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		sleepInSecond(1);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		driver.navigate().forward();
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}

	@Test
	public void TC_04_Verify_getPageSource() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		sleepInSecond(1);
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']/span/span")).click();
		sleepInSecond(1);
		String createPageSource = driver.getPageSource();
		Assert.assertTrue(createPageSource.contains("Login or Create an Account"));
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