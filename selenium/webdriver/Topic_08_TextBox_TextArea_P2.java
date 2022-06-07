package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_TextBox_TextArea_P2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String empId;
	String firstNameInput = "Hao"; 
	String lastNameInput = "Phan";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver(); //FirefoxDriver
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Login_HRM() {
		driver.get("https://opensource-demo.orangehrmlive.com/");

		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
	}

	@Test
	public void TC_02_Add_Emp() {
		//Add and Verify
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		driver.findElement(By.id("firstName")).sendKeys(firstNameInput);
		driver.findElement(By.id("lastName")).sendKeys(lastNameInput);
		empId = driver.findElement(By.id("employeeId")).getAttribute("value");
		driver.findElement(By.id("btnSave")).click();
		
		String firstNameOutput = driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value");
		Assert.assertEquals(firstNameOutput, firstNameInput);
		String lastNameOutput = driver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value");
		Assert.assertEquals(lastNameOutput, lastNameInput);
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmployeeId")).getAttribute("value"), empId);

		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmployeeId")).isEnabled());
		
		//Edit and Verify
		driver.findElement(By.xpath("//input[@id='btnSave' and @value='Edit']")).click();
		driver.findElement(By.xpath("//input[@id='personal_txtEmpFirstName']")).clear();
		driver.findElement(By.xpath("//input[@id='personal_txtEmpFirstName']")).sendKeys("Vuong");
		driver.findElement(By.xpath("//input[@id='personal_txtEmpLastName']")).clear();
		driver.findElement(By.xpath("//input[@id='personal_txtEmpLastName']")).sendKeys("Hoang");
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='personal_txtEmpFirstName']")).getAttribute("value"), "Vuong");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='personal_txtEmpLastName']")).getAttribute("value"), "Hoang");
		
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmployeeId")).isEnabled());
	}

	/*@Test
	public void TC_03() {
	}*/

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