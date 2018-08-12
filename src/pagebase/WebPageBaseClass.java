package pagebase;

import objects.login;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.ConfigurationProperties;

public class WebPageBaseClass extends BaseChromeWebDriver{

	@Parameters({"Browser"})
	@BeforeTest
	public void setupDriver(String browser){
		desiredCapabilities = new DesiredCapabilities();
		
		if(Browser.valueOf(browser) == Browser.CHROME){
			System.setProperty("webdriver.chrome.driver", ConfigurationProperties.getProperty("chromedriverpath"));
			
			ChromeOptions chromeOptions = new ChromeOptions();
			//chromeOptions.merge(desiredCapabilities);
			chromeOptions.addArguments("--start-maximized");
			
			driver = new ChromeDriver(chromeOptions);
		} else if (Browser.valueOf(browser) == Browser.FIREFOX){
			desiredCapabilities = DesiredCapabilities.firefox();
			//Set additional capabilities as needed. 
			//desiredCapabilities.setCapability("", value);
			
			FirefoxOptions firefoxOptions = new FirefoxOptions(desiredCapabilities);
			driver = new FirefoxDriver(firefoxOptions);
		}
	}
	
	@Parameters({"username", "password"})
	@Test
	public void testFunctionality(String username, String password) throws InterruptedException{
		driver.get(ConfigurationProperties.getProperty("URL"));
		
		enterText(Locator.XPATH, login.usernameXpath, username);
		enterText(Locator.XPATH, login.passwordXpath, password);
		click(Locator.XPATH, login.signInXpath);
		Thread.sleep(5000);
	}
	
	@AfterTest
	public void killDriver(){
		driver.quit();
	}
	
}
