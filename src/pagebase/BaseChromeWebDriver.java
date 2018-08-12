package pagebase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseChromeWebDriver {
	
	public WebDriver driver;
	public DesiredCapabilities desiredCapabilities;
	public WebDriverWait wait;
	
	public enum Browser{
		CHROME, FIREFOX, HEADLESS_CHROME, PHANTOM_JS, IE
	}
	
	public enum Locator{
		XPATH, ID, NAME, LINK_TEXT, CSS_SELECTOR, CLASS_NAME
	}
	
	public By by(Locator locator, String locatorValue){
		By by = null;
		
		switch(locator){
		case CLASS_NAME:
			by = By.className(locatorValue);
			break;
		case CSS_SELECTOR:
			by = By.cssSelector(locatorValue);
			break;
		case ID:
			by = By.id(locatorValue);
			break;
		case LINK_TEXT:
			by = By.linkText(locatorValue);
			break;
		case NAME:
			by = By.name(locatorValue);
			break;
		case XPATH:
			by = By.xpath(locatorValue);
			break;
		}
		
		return by;
	}
	
	public boolean isElementAvailable(Locator locator, String locatorValue){
		boolean present = false;
		
		if(driver.findElements(by(locator, locatorValue)).size()>0)
			present = true;
		
		return present;
	}
	
	public void waitUntilElementIsVisible(Locator locator, String locatorValue){
		wait.until(ExpectedConditions.visibilityOfElementLocated(by(locator, locatorValue)));
	}
	
	public void waitUntilElementIsClickable(Locator locator, String locatorValue){
		wait.until(ExpectedConditions.elementToBeClickable(by(locator, locatorValue)));
	}
	
	public void waitUntilInvisibilityOfElement(Locator locator, String locatorValue){
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by(locator, locatorValue)));
	}
	
	public void click(Locator locator, String locatorValue){
		if(isElementAvailable(locator, locatorValue))
			driver.findElement(by(locator, locatorValue)).click();
	}
	
	public void enterText(Locator locator, String locatorValue, String input){
		if(isElementAvailable(locator, locatorValue)){
			driver.findElement(by(locator, locatorValue)).clear();
			driver.findElement(by(locator, locatorValue)).sendKeys(input);
		}
	}
	
	public String getText(Locator locator, String locatorValue){
		String text = null;
		
		if(isElementAvailable(locator, locatorValue))
			text = driver.findElement(by(locator, locatorValue)).getText();
		
		return text;
	}
	
	public String getAttributeValue(Locator locator, String locatorValue, String attributeName){
		String attributeValue = null;
		
		if(isElementAvailable(locator, locatorValue))
			attributeValue = driver.findElement(by(locator, locatorValue)).getAttribute(attributeName);
		
		return attributeValue;
	}
	
}
