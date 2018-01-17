package steps;

import ValtechPropertyFileReader.ValtechPropertyFileReader;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pageobjects.ValtechPage;
import valtech.valtechdriver.ValtechWebdriver;

import java.util.concurrent.TimeUnit;



/**
 * Created by maheshboyalla on 16/01/2018.
 */
public class ValtechStepdefs {
    WebDriver valtechDriver;
    ValtechPage valtechPage;

    @Before
    public void startUp() {
        valtechDriver = new ValtechWebdriver().getDriver(ValtechPropertyFileReader.getBrowser());
        valtechDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        valtechPage = new ValtechPage(valtechDriver);
    }

    @Given("^I am on valtech home page$")
    public void iAmOnValtechHomePage() throws Throwable {
        valtechPage.navigateToValtechHomePage();
    }

    @When("^I click on Navigation \"([^\"]*)\"$")
    public void iClickOnNavigationTab(String navigationTab) throws Throwable {
        valtechPage.clickOnNavigationTab(navigationTab);
    }

    @Then("^I should see section named \"([^\"]*)\"$")
    public void iShouldSeeSectionNamed(String sectionName) throws Throwable {
        valtechPage.verifyLatestNewsSection(sectionName);
    }

    @Then("^I should see header named \"([^\"]*)\"$")
    public void iShouldSeeHeaderNamed(String sectionName) throws Throwable {
        valtechPage.verifySectionHeading(sectionName);
    }

    @When("^I click on contact page icon$")
    public void iClikcOnContactPageIcon() throws Throwable {
        valtechPage.clickOnContactPageIcon();

    }

    @Then("^I should see count contact locations$")
    public void iShouldSeeCountContactLocations() throws Throwable {
        valtechPage.verifyCountOfContactLocations();
    }

    @After
    public void tearDown() {
        valtechDriver.quit();
    }

}
