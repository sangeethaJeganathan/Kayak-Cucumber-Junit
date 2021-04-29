
package KayakPages;

import kayak.utilities.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class poFlightMainPage extends TestBase {

    Logger log = Logger.getLogger(getClass().getSimpleName());
    int startIndex =0;
    int endIndex = 0;
    String sCode = "";

    @FindBy(xpath = "//div[contains(text(),'Flights')]")
    WebElement flightLinkEle;
    @FindBy(xpath = "//h2[@class='title dark']")
    WebElement flightPageTextEle;
    @FindBy(xpath= "//div[@class='search-form-inner']//div[@data-placeholder='From?']")
    WebElement fromEle;
    @FindBy(xpath="//div[contains(@id,'origin-airport-textInputWrapper')]//input[@placeholder='From?']")
    WebElement inputFromEle;
    @FindBy(xpath = "//div[@class='search-form-inner']//div[@data-placeholder='To?']")
    WebElement toEle;
    @FindBy(xpath="//div[contains(@id,'destination-airport-textInputWrapper')]//input[@placeholder='To?']")
    WebElement inputToEle;
    @FindBy(xpath = "//div[contains(@id,'col-button-wrapper')]//button[@title='Search flights']")
    WebElement searchEle;
    @FindBy(xpath="//div[contains(@id,'origin-airport-display')]//*[name()=\"svg\"][2]")
    List<WebElement> clickNearbyEle;


    public poFlightMainPage(WebDriver driver)
    {
        TestBase.driver =driver;
        PageFactory.initElements(TestBase.driver,this);
    }

    public void clickOnFlight()
    {
        log.info("Click on the flight link");
        if(oBrowserUtil.waitForElement(flightLinkEle))
            oBrowserUtil.ufClick(flightLinkEle);
    }
    public String chkFlightPage()
    {
        log.info("make the flight page load and assert it");
        System.out.println(flightPageTextEle.getText());
        return flightPageTextEle.getText();
    }

    @FindBy(xpath = "//div[contains(@id,'origin-airport-display-multi-container')]//div[contains(@class,'js-selection-display')]")
    List<WebElement> listEle;
    ArrayList<String> al=new ArrayList<String>();

    public ArrayList<String> enterOrigin(String sOrgin) throws InterruptedException {
        log.info("Enter source city");
        if(oBrowserUtil.waitForElement(fromEle))
            oBrowserUtil.clearTheFields(fromEle);
        oBrowserUtil.performWaitOnAnElement(inputFromEle,sOrgin,"origin-airport-smartbox-dropdown");
        Thread.sleep(5000);
        for(WebElement each:listEle) {
            sCode = each.getText();
            if (!sCode.equals("")) {
                al.add(sCode.substring(sCode.indexOf("(")+1,sCode.indexOf(")")));
            }
        }
       /* log.info("select nearby airports");
        if(oBrowserUtil.waitForElement(clickNearbyEle.get(0)))
        clickNearbyEle.get(0).click();*/

        return al;
    }
    @FindBy(xpath="//div[contains(@id,'destination-airport-display-multi-container')]//div[contains(@class,'js-selection-display')]")
    List<WebElement> destEle;
    public ArrayList<String> enterDestination(String sDest) throws InterruptedException {
        al.clear();
        System.out.println("Enterting destination details");
        if(oBrowserUtil.waitForElement(toEle))
            oBrowserUtil.clearTheFields(toEle);
        oBrowserUtil.performWaitOnAnElement(inputToEle,sDest,"destination-airport-smartbox-dropdown");
        Thread.sleep(5000);
        for(WebElement each:destEle) {
            sCode = each.getText();
            if (!sCode.equals(" ")) {
                al.add(sCode.substring(sCode.indexOf("(")+1,sCode.indexOf(")")));
            }
        }
        return al;
    }

    public void enterFromDate(String startDate)
    {
        log.info("Entering start date :"+startDate);
        WebElement ele1 = driver.findElements(By.xpath("//div[contains(@id,'dateRangeInput-display-start')]//div[1]//div[contains(@class,'startDate')]//div[contains(@id,'dateRangeInput-display-start-inner')]")).get(0);
        if(oBrowserUtil.waitForElement(ele1))
            ele1.click();
        WebElement sDateEle = driver.findElement(By.xpath("//div[@aria-label='"+startDate+"']"));
        System.out.println(sDateEle);
        if(oBrowserUtil.waitForElement(sDateEle))
            sDateEle.click();
    }
    public void enterToDate(String endDate)
    {
        log.info("Enering end date:"+endDate);
       /* WebElement ele2 = driver.findElements(By.xpath("//div[contains(@id,'dateRangeInput-display-start')]//div[1]//div[contains(@class,'startDate')]//div[contains(@id,'dateRangeInput-display-start-inner')]")).get(1);
        if(oBrowserUtil.waitForElement(ele2))
            ele2.click();*/
        System.out.println("End date "+endDate);
        WebElement sToDate = driver.findElement(By.xpath("//div[@aria-label='"+endDate+"']"));
        System.out.println(sToDate);
        if(oBrowserUtil.waitForElement(sToDate))
            sToDate.click();
    }

    public void clickOnSearch() throws InterruptedException {
        if(oBrowserUtil.waitForElement(searchEle))
            searchEle.click();
        Thread.sleep(5000);
        Actions act = new Actions(driver);
        act.doubleClick(driver.findElement(By.className("Common-Layout-React-StandardHeader-container"))).build().perform();
    }

    public ArrayList<String> searchDataPage()
    {
        al.clear();
        log.info("Check all the airports details selected on the left hand side of the page");
        List<WebElement> listOfArport = driver.findElements(By.xpath("//div[@class='legsContainer']//div[@class='title']"));
        for(WebElement each:listOfArport) {
            al.add(each.getText());
        }

        return al;
    }

    public ArrayList<String> verifyResults()
    {
        al.clear();
        log.info("verifying the output for the airport details ");
        List<WebElement> airPortDets = driver.findElements(By.xpath("//span[@class='airport-name']"));
        for(int i=0;i<=1;i++)
            al.add(airPortDets.get(i).getText());

        return al;
    }

    public void displayResults()
    {
        log.info("Displayed Flight details are");
        log.info("***************************************************");
        String fromToEle= "//div[@class='col-info result-column']//ol[@class='flights']//li[@class='flight with-gutter']";
        //System.out.print(driver.findElements(By.xpath(fromToEle+"//div[@class='section times']//span[@class='time-pair']//span[1]")).get(0).getText());
        log.info("Time "+driver.findElements(By.xpath(fromToEle+"//div[@class='section times']//span[@class='time-pair']//span[1]")).get(0).getText());
        log.info(driver.findElements(By.xpath(fromToEle+"//div[@class='section times']//span[@class='time-pair']//span[2]")).get(0).getText());
        log.info("-");
        log.info(driver.findElements(By.xpath("//div[@class='section times']//span[@class='arrival-time base-time']")).get(0).getText());
        log.info(driver.findElements(By.xpath("//div[@class='section times']//span[@class='time-meridiem meridiem']")).get(0).getText());
       log.info("flight is :"+driver.findElements(By.xpath("//div[@class='section times']//div[@class='bottom ']")).get(0).getText());

        log.info("total Stops are          :"+driver.findElements(By.xpath("//div[@class='section stops']//div//span")).get(0).getText());
        log.info("The layover airports are :");
        log.info(driver.findElements(By.xpath("//div[@class='section stops']//div[@class='bottom']//span[1]")).get(0).getText());
        log.info(driver.findElements(By.xpath("//div[@class='section stops']//div[@class='bottom']//span[2]")).get(0).getText());

        log.info("travel time :"+driver.findElements(By.xpath("//div[@class='section duration allow-multi-modal-icons']//div[@class='top']")).get(0).getText());
        log.info("From :"+driver.findElements(By.xpath("//span[@class='airport-name']")).get(0).getText());
        log.info("To   :"+driver.findElements(By.xpath("//span[@class='airport-name']")).get(1).getText());
        log.info("**********************************************************");
    }
}

