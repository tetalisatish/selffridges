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
public class brows1 {
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
	@FindBy(xpath="//span[text()='Low Card']") WebElement lowcard;
	@FindBy(xpath="//span[text()='High Card']") WebElement highcard;
	@FindBy(xpath="//button[text()='+500']") WebElement fiveHundredbetamount;
	@FindBy(xpath="//button[contains(text(),'Place bets')]") WebElement placebets;
	@FindBy(xpath="//div[@class='timer-new']//label") WebElement timeelement;
	@FindBy(xpath="//span[@class='selection-name']") WebElement verifytext;
	@FindBy(xpath="//button[@class='apl-btn apl-btn-mini apl-btn-remove']") WebElement removebutton;
	@FindBy(xpath="//span[text()='High Card']//child:span") WebElement betstake;
	static String oldcardurl="";
	static String newcardurl="";
	static int amount=1000;


	public WebDriver driver;


	public brows1(WebDriver driver) {
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

	public void switchtoframe(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
	}

	public void wait(WebDriver d,int n) {
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(n));
	}

	@Test
	public void sho() throws IOException, InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(240));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		data(phonenumber,"9154844333");
		data(password,"Tsr@2247");
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
			wait.until(ExpectedConditions.attributeToBe(lowchangeelement,"class", "high-low-body__cell"));
			totalcards=i;
			String oldcardurl=jokercard.getAttribute("src");
			String oldcardsuburl=oldcardurl.substring(oldcardurl.length()-8, oldcardurl.length()-1);
			String oldcardnumber=oldcardsuburl.replaceAll("[^0-9]","");
			System.out.println(oldcardurl);
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					String currentAttributeValue = jokercard.getAttribute("src");
					return !currentAttributeValue.equals(oldcardurl);	
				}
			});
			String newcardurl=jokercard.getAttribute("src");
			String newcardsuburl=newcardurl.substring(newcardurl.length()-8, newcardurl.length()-1);
			String newcardnumber=newcardsuburl.replaceAll("[^0-9]","");		
			if(oldcardnumber.equalsIgnoreCase("5")){
				if(newcardnumber.equalsIgnoreCase("2")||
						newcardnumber.equalsIgnoreCase("3")||
						newcardnumber.equalsIgnoreCase("4")||
						newcardnumber.equalsIgnoreCase("5")){
					amount=amount-100;
				}	
				else {
					amount =amount+41;
				}
			}

			if(oldcardnumber.equalsIgnoreCase("6")){
				if(newcardnumber.equalsIgnoreCase("2")||
						newcardnumber.equalsIgnoreCase("3")||
						newcardnumber.equalsIgnoreCase("4")||
						newcardnumber.equalsIgnoreCase("5")||
						newcardnumber.equalsIgnoreCase("6")){
					amount=amount-100;
				}
				else {
					amount =amount+61;
				}
			}
			if(oldcardnumber.equalsIgnoreCase("7")){
				if(newcardnumber.equalsIgnoreCase("2")||
						newcardnumber.equalsIgnoreCase("3")||
						newcardnumber.equalsIgnoreCase("4")||
						newcardnumber.equalsIgnoreCase("5")||
						newcardnumber.equalsIgnoreCase("6")||
						newcardnumber.equalsIgnoreCase("7")){
					amount=amount-100;
				}	
				else {
					amount =amount+87;
				}
			}
			if(oldcardnumber.equalsIgnoreCase("11")){
				if(newcardnumber.equalsIgnoreCase("12")||
						newcardnumber.equalsIgnoreCase("13")||
						newcardnumber.equalsIgnoreCase("11")||
						newcardnumber.equalsIgnoreCase("1")){
					amount=amount-100;
				}	
				else {
					amount =amount+41;
				}
			}
			if(oldcardnumber.equalsIgnoreCase("9")){
				if(newcardnumber.equalsIgnoreCase("12")||
						newcardnumber.equalsIgnoreCase("13")||
						newcardnumber.equalsIgnoreCase("1")||
						newcardnumber.equalsIgnoreCase("11")||
						newcardnumber.equalsIgnoreCase("9")){
					amount=amount-100;
				}
				else {
					amount =amount+61;
				}
			}
			if(oldcardnumber.equalsIgnoreCase("8")){
				if(newcardnumber.equalsIgnoreCase("12")||
						newcardnumber.equalsIgnoreCase("13")||
						newcardnumber.equalsIgnoreCase("1")||
						newcardnumber.equalsIgnoreCase("9")||
						newcardnumber.equalsIgnoreCase("8")||
						newcardnumber.equalsIgnoreCase("11")){
					amount=amount-100;
				}	
				else {
					amount =amount+87;
				}
			}
			System.out.println("Amount virtual - "+amount);
		}

	}
}







