package KayakStepDefinitions;

import KayakPages.poFlightMainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kayak.utilities.TestBase;
import org.junit.Assert;

public class FlightMainPageTest extends TestBase{

    poFlightMainPage oFlightMainPage;
    @Given("launch the URL")
    public void launchTheURL() {
    driver.get(oCons.getKayakUrl());
        oFlightMainPage = new poFlightMainPage(driver);
    }

    @When("click on the flight link")
    public void clickOnTheFlightLink() {
        oFlightMainPage.clickOnFlight();
        
    }

    @Then("check the search page loaded with the search details")
    public void checkTheSearchPageLoadedWithTheSearchDetails() {

        Assert.assertEquals(oFlightMainPage.chkFlightPage(),"Search hundreds of flight sites at once.");
    }

//    @When("Enter the origin and destination")
//    public void enterTheOriginAndDestination() {
//        oFlightMainPage.clickOnFlight();
//        oFlightMainPage.enterOrigin();
//        oFlightMainPage.enterDestination();
//    }
//
//    @And("Enter From and to date")
//    public void enterFromAndToDate() {
//        oFlightMainPage.enterFromDate();
//        oFlightMainPage.enterToDate();
//
//    }
//
//    @Then("click on the search button")
//    public void clickOnTheSearchButton() {
//        oFlightMainPage.clickOnSearch();
//    }
}
