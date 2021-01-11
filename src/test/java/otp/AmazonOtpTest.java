package otp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonOtpTest 
{

	public static void main(String[] args) 
	{
		//open browser and launch site
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://www.amazon.in/");
		WebDriverWait w=new WebDriverWait(driver,20);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Account & Lists']")));
		WebElement e=driver.findElement(By.xpath("//span[text()='Account & Lists']"));
		Actions a=new Actions(driver);
		a.moveToElement(e).build().perform();
		w.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Start here.'])[1]"))).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.name("customerName"))).sendKeys("suchindrasupraja");
		w.until(ExpectedConditions.visibilityOfElementLocated(By.name("countryCode")));
		WebElement e1=driver.findElement(By.name("countryCode"));
		Select s=new Select(e1);
		s.selectByValue("US");
		driver.findElement(By.name("email")).sendKeys("2018842406");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.id("continue")).click();
		// connect to twilio cloud for sms service
		String authid="ACa6e000f61d50ce68a14981410f13069e";
		String authtoken="c1c1eb8634b7ccaaefd9bcf006c783e2";
		Twilio.init(authid, authtoken);
		ResourceSet<Message> messages=Message.reader().read();
		String temp=messages.iterator().next().getBody();
		String[] pieces=temp.split(" ");
		String otp=pieces[0];
		//enter otp in page element
		w.until(ExpectedConditions.visibilityOfElementLocated(By.name("code"))).sendKeys(otp);

	}

}
