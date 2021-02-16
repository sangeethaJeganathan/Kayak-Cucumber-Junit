package kayak.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowserUtilities {

    //initialising logger
    Logger log = Logger.getLogger(getClass().getSimpleName());


    public void launchBrowser(String sBrowserType) throws Exception {
        log.info("Checking the browser type "+sBrowserType);
        if(sBrowserType.toLowerCase().startsWith("ch"))
        {
            //setting up the chromedriver();
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-notifications");
            TestBase.driver = new ChromeDriver(chromeOptions);
        }
        else if((sBrowserType.toLowerCase().startsWith("ff")) || (sBrowserType.toLowerCase().startsWith("fi")))
        {
            //setting up the firefox driver();
            WebDriverManager.firefoxdriver().setup();
            /*FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setPreference("permissions.default.desktop-notification", 1);
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setCapability(FirefoxDriver.PROFILE,firefoxProfile);*/
            TestBase.driver = new FirefoxDriver();
        }
        else if (sBrowserType.equalsIgnoreCase("Edge"))
        {
            WebDriverManager.edgedriver().setup();
            TestBase.driver = new EdgeDriver();
        }
        else if(sBrowserType.equalsIgnoreCase("ie"))
        {
            WebDriverManager.iedriver().setup();
            TestBase.driver = new InternetExplorerDriver();
        }
        else {
            // If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
        TestBase.driver.manage().window().maximize();
        TestBase.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        TestBase.driver.manage().deleteAllCookies();
    }

    public boolean waitForElement(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(TestBase.driver,3000);
        wait.until(ExpectedConditions.visibilityOf(element));
        if(element.isDisplayed())
            return true;
        else
            return false;
    }
    public void ufClick(WebElement ele)
    {
        ele.click();
    }

    public void ufSendKeys(WebElement element, String sValueToEnter)
    {
        //element.clear();
        element.sendKeys(sValueToEnter);
    }
    public void clearTheFields(WebElement element)
    {
        log.info("Performing KEYS.BACKSPACE to clear the field "+element);
        int i=4;
        while (i!=0) {
            // "\u0008" - is backspace char
            element.sendKeys("\u0008");
            i--;
        }
    }

    public void performWaitOnAnElement(WebElement element,String sVal,String sFromTo)
    {
        WebDriverWait wait = new WebDriverWait(TestBase.driver,30);
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(sVal);

        List<WebElement> selectVal = TestBase.driver.findElements(By.xpath("//div[contains(@id,'"+sFromTo+"')]//li//div[@class='item-info']//div[1]"));
        for(WebElement each:selectVal) {
            if (each.getText().contains(sVal))
            {
                each.click();
                //TestBase.driver.findElements(By.xpath("//div[contains(@id,'origin-airport-smartbox-dropdown')]//li//div[3]//child::label")).get(iCnt).click();
                break;
            }
        }
      /* element.sendKeys(Keys.ENTER);
        element.sendKeys(Keys.ENTER);*/
    }


}
