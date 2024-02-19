package openbrows;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class demo3 {
	public WebDriver driver;

	@Test
	public void test() throws InterruptedException, IOException {
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		driver=new ChromeDriver(ops);
		driver.manage().window().maximize();
		driver.get("https://thefairplay.io");
		brows b=new brows(driver);
		b.sho();
	}
}
