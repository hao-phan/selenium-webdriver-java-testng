package webdriver;

import java.security.Key;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver(); //FirefoxDriver
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Element() {
		// Muốn thao tác với Element thì phải tìm Element trước
		// Sau đó mới áp dụng hành vi vào Element
		
		// 1-Tìm Element
		// 2-Với loại Locator là gì
		// 3-Tương tác lên kiểm tra nó
		
		//Muốn thao tác trực tiếp lên Element thì không cần khai báo biến (Khi chỉ sử dụng 1 lần)
		driver.findElement(By.id("send2"));
		
		//Thao tác với biến đó từ 2 lần trở lên thì cần phải khai báo biến để tránh việc lặp lại
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("email")).isDisplayed();
			//Thay vào đó ta sẽ khai báo biến như bên dưới để tránh việc lặp lại
		WebElement emailAddress = driver.findElement(By.id("email"));
		emailAddress.clear();
		emailAddress.sendKeys("");
		emailAddress.isDisplayed();
	}
	
	@Test
	public void TC_01_Element_Method() {
		WebElement element = driver.findElement(By.id(""));
		
		// Xóa dữ liệu trong những field cho phép nhập
		// Luôn luôn clear hết dữ liệu trước khi thao tác lên field đó
		// Textbox/ TextArea/ Editable Dropdown
		element.clear();
		
		// Nhập dữ liệu trong những field cho phép nhập
		element.sendKeys("");
		element.sendKeys(Keys.ENTER);
		
		// Không khai báo biến = verify trực tiếp
		Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"), "Search entire store here...");
		
		// Khai báo biến để dùng nhiều lần
		String searchTextBoxPlaceHolder = driver.findElement(By.id("search")).getAttribute("placeholder");
		Assert.assertEquals(searchTextBoxPlaceHolder, "Search entire store here...");
		
		//GUI: Font type/ Font Size/ Color/ Pixel/ ...
		element.getCssValue("background-color"); // rgb(149, 246, 6)
		element.getCssValue("font-size"); // 13px
		
		// Framework: Attach screenshot to Report HTML
		element.getScreenshotAs(OutputType.FILE);
		
		// Truyền 1 biến vào 1 chuỗi
		driver.findElement(By.xpath("//" + searchTextBoxPlaceHolder + "[@id='email']")); // Chuỗi 1 + Biến + Chuỗi 2
		
		// Lấy ra Text của Element hiện tại
		// Text của những Element con bên trong
		element.getText();
		
		// Mong muốn 1 Element hiển thị/ Không hiển thị
		// Ngược lại với Undisplay
		// Hiển thị: Người dùng nhìn thấy được/ có kích thước cụ thể (Chiều cao, chiều rộng)
		// Áp dụng cho tất cả các loại Element: Textbox/ TextArea/ dropdown/ checkbox/ radio/ button, ...
		element.isDisplayed();
		
		// Mong muốn 1 Element có thể thao tác được lên hoặc ko
		// Ngược lại với Disable
		// Thao tác được: Enable
		// không thao tác được: Disblae
		// Áp dụng cho tất cả các loại Element: Textbox/ TextArea/ dropdown/ checkbox/ radio/ button, ...
		element.isEnabled();
		
		// Mong muốn Element được chọn hay chưa
		// Áp dụng cho 1 vài Element: Checkbox/ Radio butoton/ Dropdown (Default)
		element.isSelected();
		
		// Click vào 1 Element
		// Button/ Link/ Checkbox/ Radio/ Image/ Icon, ...
		element.click();
		
		// Giống hành vi ENTER ở các form
		// Dùng cho tagname: form (element con bên trong mới sử dụng được)
		element.submit();
		
	}

	/*@AfterClass
	public void afterClass() {
		driver.quit();
	}*/
}