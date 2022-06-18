package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Handle_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver(); //FirefoxDriver
		
		expliciWait = new WebDriverWait(driver, 15);//Luôn khởi tạo sau driver->nó cần giá trị driver để khởi tạo expliciWait lên //Wait cho các element theo điều kiện có sẵn: visible/invisible/presence/clickable....
		
		//Wait cho viecj "tìm" element: findElement/findElements
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// Chọn item 10 
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "10"); //Viết 1 cái hàm bên dưới để dễ quản lí và dùng lại được nhiều lần

		// Chọn item 5
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "5"); //Viết 1 cái hàm bên dưới để dễ quản lí và dùng lại được nhiều lần
		
	}
	
	
	//Viết hàm để có thể sử dụng lại được nhiều lần
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String expectedLocator) {
		// Click vào dropdown cho xổ hết ra tất cả các item con bên trong => Click
				driver.findElement(By.xpath(parentLocator)).click(); 
				sleepInSecond(1);
				
				// Chờ cho tất cả các item con bên trong được load ra => WebDriverWait
				// By Locator = đại diện cho "Tất cả các item con bên trong"
				// Lấy cái locator đến cái thẻ chưa text item
				expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));
				
				//Tìm item mong muốn (nếu như ko hiển thị thì cần cuộn chuột xuống để tìm
				// =>Vòng lặp để lấy hết tất cả các item rồi duyệt qua - getText từng cái
				
				//Lấy hết tất cả các item ra lưu vào 1 List Element
				List<WebElement> allDropDowItems = driver.findElements(By.xpath(childLocator)); //19 items trong này
				
				//Duyệt qua vòng lặp
				for (WebElement item : allDropDowItems) {
					String actualTextItem = item.getText();
					System.out.println("Item text = " + actualTextItem);
					
					/// Thấy item cần chọn thì click vào => So sánh với item mong muốn sau đó Click vào
					if(actualTextItem.equals(expectedLocator)) {
						item.click();
						sleepInSecond(1);
						break;//Thoát khỏi vòng lặp khi đã chọn được giá trị mong muốn
					}
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