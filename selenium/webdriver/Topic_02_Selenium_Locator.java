package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	//Khai báo 1 biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		//Set chromedriver: giao tiếp giữa broswer và code
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		
		//Bật trình duyệt chrome lên
		driver = new ChromeDriver(); //FirefoxDriver
		
		//Set thời gian đi tìm element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Bật browser to lên
		driver.manage().window().maximize();
		
		//Mở app ra
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Css() {
		//Id
		//Class
		//Name
		//TagName
		//Link Text
		//Partial Link Text
		
		//Css
			//Id
		driver.findElement(By.cssSelector("input[id='email']"));
		driver.findElement(By.cssSelector("input#email]"));
		driver.findElement(By.cssSelector("#email]"));
		
			//Class
		driver.findElement(By.cssSelector("img[class='fb_logo']"));
		driver.findElement(By.cssSelector("input.inputtext"));
		driver.findElement(By.cssSelector(".inputtext]"));
		
			//Name
		driver.findElement(By.cssSelector("input[name='email']"));
		
			//Tagname
		driver.findElement(By.cssSelector("a"));
		
			//Link Text: Css không làm việc với Text cho nên nó sẽ làm việc thông qua thẻ a
		driver.findElement(By.cssSelector("a[title='Vietnamese']"));
		
			//Partial Link Text: Css không làm việc với Text cho nên nó sẽ làm việc thông qua thẻ a
		driver.findElement(By.cssSelector("a[title*='Vietnam']"));
	}
	

	@Test
	public void TC_02_Xpath() {
		//Xpath
			//Id
		driver.findElement(By.xpath("//input[@id='email']"));
		
			//Class
		driver.findElement(By.xpath("//img[@class='fb_logo _8ilh img']"));
		driver.findElement(By.xpath("//img[contains(@class.'fb_logo')]")); //search 1 phần text
		driver.findElement(By.xpath("//img[start-with(@class.'fb_logo')]")); //search 1 phần text
		
			//Name
		driver.findElement(By.xpath("//input[@name='email']"));
		
			//Tagname
		driver.findElement(By.xpath("//a"));
		
			//Link Text
		driver.findElement(By.xpath("//a[@title='Vietnamese']"));
		driver.findElement(By.xpath("//a[text()='Tiếng Việt']"));
		
			//Partial Link Text:
		driver.findElement(By.xpath("//a[contains(@title,'Vietnam')]"));
		driver.findElement(By.xpath("//a[contains(text(),'Tiếng')]"));
		driver.findElement(By.xpath("//a[contains(text(),'Việt')]"));
		driver.findElement(By.xpath("//a[contains(text(),'Tiếng Việt')]"));
	}


	/*@AfterClass
	public void afterClass() {
		driver.quit();
	}*/
}