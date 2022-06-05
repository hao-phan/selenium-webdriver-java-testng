package webdriver;

import java.awt.Point;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Browser_P1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void TC_01_Method() {
		driver.close(); //Dùng để close Browser or Tab. Nếu có nhiều tabs thì nó sẽ closed tab đag active
		driver.quit(); //Dùng để đóng hết tất cả không quan tâm bao nhiêu Tabs --Sử dụng nhiều--
		driver.get(""); //Dùng để mở 1 URL --Sử dụng nhiều--
		driver.findElement(By.xpath("")); //Dùng để tìm 1 Element --Sử dụng nhiều--
		driver.findElements(By.xpath("")); //Dùng để tìm nhiều Elements --Sử dụng nhiều--
		driver.getCurrentUrl(); //Lấy ra URL của page hiện tại
		driver.getPageSource(); //Lấy ra cái source code (HTML, CSS, JS) của page hiện tại
		driver.getTitle(); //Lấy ra cái title của page hiện tại
		driver.getWindowHandle(); //Dùng để xử lí Window/ Tab. Lấy ra ID của Window/ Tab đang active --Sử dụng nhiều--
		driver.getWindowHandles(); //Dùng để xử lí Window/ Tab. Lấy ra tất cả các ID của các Window/ Tabs đang active --Sử dụng nhiều--
		driver.manage().deleteAllCookies(); //Cookie: Lưu lại phiên đăng nhập/ session/ dữ liệu duyệt web, .... **Sẽ được học ở phần Framwork.***
		driver.manage().logs(); // **Sẽ được học ở phần Framwork.***
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //Chờ cho findElement/findElements --Sử dụng nhiều--
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS); //Chờ cho 1 pageload thành công
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS); //Chờ cho 1 đoạn code javascript được thực thi thành công. **Sẽ được học ở bài Javascript Excutor**
		driver.manage().window().fullscreen();//Full toàn màn hình
		driver.manage().window().maximize();//Mở rộng hết cửa sổ không tràn viền --Sử dụng nhiều--
		//driver.manage().window().setPosition(new Point(0, 0));//Set vị trí của browser so với độ phân giải màn hình (Resolution)
		driver.manage().window().getPosition();
		driver.manage().window().setSize(new Dimension(1920, 1080));//Mở browser với kích thước là bao nhiêu. Dùng để test Responsive
		driver.manage().window().getSize();
		
		//Tracking history tốt hơn
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to(""); //Mở 1 URL
		
		// ******** Sẽ được học ở Alert tại framework ***********
		driver.switchTo().alert(); //--Sử dụng nhiều--
		
		// ******** Sẽ được học ở Frame/Iframe tại framework ***********
		driver.switchTo().frame(0); //--Sử dụng nhiều--
		
		// ******** Sẽ được học ở Window/Tabs tại framework ***********
		driver.switchTo().window(""); //--Sử dụng nhiều--
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}