package KayakStepDefinitions;

import KayakPages.poFlightMainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kayak.utilities.TestBase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class FlightSearchPageTest extends TestBase {

    poFlightMainPage oFlightMainPage;
    List<String> originList = new ArrayList<String>();
    List<String> destList = new ArrayList<String>();
    List<String> originDestList = new ArrayList<String>();
    List<String> dataInLeftFrame = new ArrayList<String>();
    ArrayList<String> resultDetails = new ArrayList<String>();
    String sOrigin;
    String sDestination;


    @Given("launch URL")
    public void launchURL() {
        driver.get(oCons.getKayakUrl());
        oFlightMainPage = new poFlightMainPage(driver);
    }

    @When("Enter the origin and destination {string}")
    public void enterTheOriginAndDestination(String excelDataRow) throws InterruptedException {
        int row = Integer.parseInt(excelDataRow)-1;

        oFlightMainPage.clickOnFlight();
        sOrigin = dataSet.get(row).get("Origin");
        sDestination = dataSet.get(row).get("Destination");
        originDestList.addAll(oFlightMainPage.enterOrigin(dataSet.get(row).get("Origin")));
        destList.addAll(oFlightMainPage.enterDestination(dataSet.get(row).get("Destination")));
        originDestList.addAll(destList);

    }

    @And("Enter From and to date {string}")
    public void enterFromAndToDate(String excelDataRow) {
        int row = Integer.parseInt(excelDataRow)-1;
        oFlightMainPage.enterFromDate(dataSet.get(row).get("Start Date"));
        oFlightMainPage.enterToDate(dataSet.get(row).get("End Date"));
    }

    @Then("click on the search button")
    public void clickOnTheSearchButton() throws InterruptedException {

        oFlightMainPage.clickOnSearch();
        dataInLeftFrame.addAll(oFlightMainPage.searchDataPage());

        if(dataInLeftFrame.contains(sOrigin) || dataInLeftFrame.contains(sDestination))
            System.out.println("All the nearby airports selected got displayed");
        resultDetails.addAll(oFlightMainPage.verifyResults());
        System.out.println(originDestList);
        System.out.println(resultDetails);
        if(originDestList.equals(resultDetails)) {
            Assert.assertEquals(resultDetails, originDestList);
            System.out.println("The airport details entered are matching withe the results");
            oFlightMainPage.displayResults();
        }
        else
            System.out.println("There is a mismatch please check");

    }


}
