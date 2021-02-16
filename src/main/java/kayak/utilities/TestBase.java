package kayak.utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;


public class TestBase {

    Logger log = Logger.getLogger(getClass().getSimpleName());
    public static WebDriver driver;
    public static BrowserUtilities oBrowserUtil = new BrowserUtilities();
    public static CommonUtilities oCommUtil = new CommonUtilities();
    public static Constants oCons = new Constants();
    public static List<HashMap<String,String>> dataSet;

    public void initAllDependents(String sBrowserType) throws Exception {
        //oCommUtil.loadLog4jPropertyFile(System.);
        log.info("Launching the respective browser");
      //  oCommUtil.loadJsonFile(System.getProperty("user.dir")+"/src/main/java/resources/config.json");
        oCommUtil.loadLog4jPropertyFile(System.getProperty("user.dir") + "/src/main/java/properties/log4j.properties");
        dataSet= oCommUtil.readExcelDataFromFile(System.getProperty("user.dir")+"/src/main/java/TestData/KayakData.xlsx","Sheet1");
    oBrowserUtil.launchBrowser(sBrowserType);
    }


    public void closeAllDependents()
    {
        driver.quit();
    }
}
