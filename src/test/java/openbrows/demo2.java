package openbrows;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class demo2 {
	public WebDriver driver;

	@Test
	public void test() throws InterruptedException, IOException {
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		driver=new ChromeDriver(ops);
		driver.manage().window().maximize();
		driver.get("https://thefairplay.io");
		brows1 b=new brows1(driver);
		b.sho();
	}
}
