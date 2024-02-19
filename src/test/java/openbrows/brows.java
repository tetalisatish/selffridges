package openbrows;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class brows {
	static int totalcards,lowcard_number,Highcard_turns,Highcard_number,lowcard_turns,lowcard_loss,Highcard_loss,middlecard_number,highorlow_turns,middlecard_loss=0;
	@FindBy(xpath="//span[text()='Login']") WebElement Login;
	@FindBy(xpath="//label[text()='Email/Mobile Number ']//following::input[@type='text']") WebElement phonenumber;
	@FindBy(xpath="//input[@placeholder='Password']") WebElement password;
	@FindBy(xpath="//span[contains(text(),'Sign')]") List<WebElement> login;
	@FindBy(xpath="//button[@class='close-button v-btn v-btn--icon v-btn--round theme--dark v-size--default']") WebElement intobutton;
	@FindBy(xpath="//div[contains(text(),'Live Card')]") WebElement Livecard;
	@FindBy(xpath="//a[@to='live-cards/56968']") WebElement Hilow;
	@FindBy(id="teenpattiFrame") WebElement frameid;
	@FindBy(xpath="//label[@for='toggleClassicView']") WebElement viewtype;
	@FindBy(xpath="//span[@class='modern-ui-toggle-switch-button']") WebElement viewbutton;
	@FindBy(xpath="//*[@fill='url(#hoverTransparencyVertical)']") WebElement hoverbutton;
	@FindBy(id="playButton-playerDiv") WebElement playbutton;
	@FindBy(xpath="//span[text()='Joker']//following-sibling::div//img") WebElement jokercard;
	@FindBy(xpath="//span[text()='Low Card']//parent::div") WebElement lowchangeelement;
	@FindBy(xpath="//span[text()='High Card']//parent::div") WebElement highchangeelement;
	@FindBy(xpath="//span[text()='High Card']") WebElement highcard;
	@FindBy(xpath="//span[text()='Low Card']") WebElement lowcard;
	@FindBy(xpath="//button[text()='+500']") WebElement fiveHundredbetamount;
	@FindBy(xpath="//button[contains(text(),'Place bets')]") WebElement placebets;
	@FindBy(xpath="//span[@class='selection-name']") WebElement verifytext;
	@FindBy(xpath="//div[@class='timer-new']//label") WebElement timeelement;

	static int five_card,five_lows,five_highs,five_snaps;
	static int J_card,J_lows,J_highs,J_snaps;
	static int amount=3000;
	static int realmoney=3000;
	static String oldcardurl="";
	static String newcardurl="";

	public WebDriver driver;


	public brows(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}


	public void click(WebElement ele) throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
		}
		catch(Exception e) {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
		}
	}
	public void waitforandclick(WebElement ele) throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(300));
		try {
			wait.until(ExpectedConditions.visibilityOf(ele)).click();
		}
		catch(Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
		}
	}

	public void mousehover(WebDriver d,WebElement ele) {
		Actions a=new Actions(d);
		a.moveToElement(ele).perform();
	}

	public void data(WebElement ele,String data) {
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(ele)).sendKeys(data);
		}
		catch(Exception e) {
			ele.sendKeys(data);
		}
	}

	public void wait(WebDriver d,int n) {
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(n));
	}

	public void switchtoframe(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
	}

	@Test
	public void sho() throws IOException, InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(120));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		data(phonenumber,"9393729999");
		data(password,"Hari@2247");
		click(Login);
		waitforandclick(intobutton);
		click(Livecard);
		try {
			click(Hilow);
		}
		catch(Exception e) {
			js.executeScript("arguments[0].click();", Hilow);
		}
		switchtoframe(frameid);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		mousehover(driver,hoverbutton);
		click(playbutton);
		String value=viewtype.getAttribute("class");
		if(value!="ng-tns-c1-0") {
			click(viewbutton);
		}
		for(int i=1;i<=2000;i++) {
			totalcards=i;
			String oldurl=jokercard.getAttribute("src");
			String oldcardsuburl=oldurl.substring(oldurl.length()-8, oldurl.length()-1);
			String oldcardnumber=oldcardsuburl.replaceAll("[^0-9]","");
			System.out.println(oldurl);
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					String currentAttributeValue = jokercard.getAttribute("src");
					return !currentAttributeValue.equals(oldurl);	
				}
			});
			String newcurl=jokercard.getAttribute("src");
			String newcardsuburl=newcurl.substring(newcurl.length()-8, newcurl.length()-1);
			String newcardnumber=newcardsuburl.replaceAll("[^0-9]","");		
			if(oldcardnumber.equalsIgnoreCase("5")){
				five_card++;
				if(newcardnumber.equalsIgnoreCase("2")||
						newcardnumber.equalsIgnoreCase("3")||
						newcardnumber.equalsIgnoreCase("4")){
					five_lows++;
					amount=amount+285;
				}
				else if(newcardnumber.equalsIgnoreCase("5")) {
					five_snaps++;
					amount=amount-100;
				}	
				else {
					five_highs++;
					amount =amount-100;
				}
			}
			if(oldcardnumber.equalsIgnoreCase("11")){
				J_card++;
				if(newcardnumber.equalsIgnoreCase("12")||
						newcardnumber.equalsIgnoreCase("13")||
						newcardnumber.equalsIgnoreCase("1")){
					J_highs++;
					amount=amount+285;
				}
				else if(newcardnumber.equalsIgnoreCase("11")) {
					J_snaps++;
					amount=amount-100;
				}	
				else {
					J_lows++;
					amount =amount-100;
				}
			}
			System.out.println("Amount virtual - "+amount);
			if(amount<=2500) {
				
			}
		}
		for(int i=1;i<=2000;i++) {
			totalcards=i;
			wait.until(ExpectedConditions.attributeToBe(lowchangeelement,"class", "high-low-body__cell"));
			oldcardurl=jokercard.getAttribute("src");
			String oldcardsuburl=oldcardurl.substring(oldcardurl.length()-8, oldcardurl.length()-1);
			String oldcardnumber=oldcardsuburl.replaceAll("[^0-9]","");
			System.out.println(oldcardurl);
			if(oldcardnumber.equalsIgnoreCase("5")){
				wait(driver,5);
				js.executeScript("arguments[0].click();", lowchangeelement);
				wait(driver,5);
				js.executeScript("arguments[0].click();", fiveHundredbetamount);
				wait(driver,5);
				js.executeScript("arguments[0].click();", placebets);
				five_card++;
			}
			if(oldcardnumber.equalsIgnoreCase("11")){
				wait(driver,5);
				js.executeScript("arguments[0].click();",highchangeelement);
				wait(driver,5);
				js.executeScript("arguments[0].click();", fiveHundredbetamount);
				wait(driver,5);
				js.executeScript("arguments[0].click();", placebets);
				J_card++;
			}
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					String currentAttributeValue = jokercard.getAttribute("src");
					return !currentAttributeValue.equals(oldcardurl);	
				}
			});
			newcardurl=jokercard.getAttribute("src");
			String newcardsuburl=newcardurl.substring(newcardurl.length()-8, newcardurl.length()-1);
			String newcardnumber=newcardsuburl.replaceAll("[^0-9]","");	
			if(oldcardnumber.equalsIgnoreCase("5")){
				if(newcardnumber.equalsIgnoreCase("2")||
						newcardnumber.equalsIgnoreCase("3")||
						newcardnumber.equalsIgnoreCase("4")){
					five_lows++;
					realmoney=realmoney+285;
				}
				else if(newcardnumber.equalsIgnoreCase("5")) {
					five_snaps++;
					realmoney=realmoney-100;
				}	
				else {
					five_highs++;
					realmoney =realmoney-100;
				}
			}
			if(oldcardnumber.equalsIgnoreCase("11")){
				if(newcardnumber.equalsIgnoreCase("12")||
						newcardnumber.equalsIgnoreCase("13")||
						newcardnumber.equalsIgnoreCase("1")){
					J_highs++;
					realmoney=realmoney+285;
				}
				else if(newcardnumber.equalsIgnoreCase("11")) {
					J_snaps++;
					realmoney=realmoney-100;
				}	
				else {
					J_lows++;
					realmoney =realmoney-100;
				}
			}
			System.out.println("Amount real - "+realmoney);
			if(realmoney<=2000) {
				System.out.println("Total turns - "+totalcards);
				System.out.println("Total 5 turns - "+five_card);
				System.out.println("Total 5 lows - "+five_lows);
				System.out.println("Total 5 highs - "+five_highs);
				System.out.println("Total 5 snaps - "+five_snaps);
				System.out.println("Total J turns - "+J_card);
				System.out.println("Total J lows - "+J_lows);
				System.out.println("Total J highs - "+J_highs);
				System.out.println("Total J snaps - "+J_snaps);
				driver.quit();
				Assert.assertTrue(false);
			}
			if(realmoney>=3100) {
				realmoney=3000;
				break;
			}
		}
		System.out.println("Total turns - "+totalcards);
		System.out.println("Total 5 turns - "+five_card);
		System.out.println("Total 5 lows - "+five_lows);
		System.out.println("Total 5 highs - "+five_highs);
		System.out.println("Total 5 snaps - "+five_snaps);
		System.out.println("Total J turns - "+J_card);
		System.out.println("Total J lows - "+J_lows);
		System.out.println("Total J highs - "+J_highs);
		System.out.println("Total J snaps - "+J_snaps);
		System.out.println("satishreddy");
		System.out.println("tata reddy");
		driver.quit();
	}
}







