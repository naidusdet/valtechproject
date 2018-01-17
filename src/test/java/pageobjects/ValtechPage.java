package pageobjects;

import ValtechPropertyFileReader.ValtechPropertyFileReader;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


import static org.junit.Assert.assertEquals;
import static valtech.valtechconstants.ValtechConfigConstants.TEST_CONFIG_PROP_FILE;


/**
 * Created by maheshboyalla on 16/01/2018.
 */
public class ValtechPage extends BasePage{

    @FindBy(xpath = "//body/div[1]/main/div[2]/div[3]/div[1]/header/h2")
    WebElement sectionLatestNews;

    @FindBy(xpath = "//a[contains(@href,'/about/')]")
    WebElement sectionAbout;

    @FindBy(xpath = "//body/div[1]/main/div[1]/h1")
    WebElement headerAbout;

    @FindBy(xpath = "//a[contains(@href,'/services/')]")
    WebElement sectionServices;

    @FindBy(xpath = "//body/div[1]/main/section/header/h1")
    WebElement headerServices;

    @FindBy(xpath = "//a[contains(@href,'/work/')]")
    WebElement sectionWork;

    @FindBy(xpath = "//body/div[1]/main/header/h1")
    WebElement headerWork;

    @FindBy(xpath = "//body/div[1]/header/div/nav/div/div[1]/label[1]/div/a/div/div/div[1]/i")
    WebElement contactIcon;

    public ValtechPage(WebDriver valtechDriver) {
        super(valtechDriver);
        PageFactory.initElements(driver,this);

    }

    public ValtechPage navigateToValtechHomePage() {
       driver.get("https://"+ ValtechPropertyFileReader.getBaseURL());
        return this;
    }
   public ValtechPage verifyLatestNewsSection(String sectionName){
      String actualSectionName =  sectionLatestNews.getText();
       assertEquals(sectionName,actualSectionName);
       return this;
   }
    public ValtechPage clickOnNavigationTab(String headerTab){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        if(headerTab.equals("ABOUT")){
            wait.until(ExpectedConditions.elementToBeClickable(sectionAbout)).click();
            return this;

        }
        else if (headerTab.equals("WORK")){
            wait.until(ExpectedConditions.elementToBeClickable(sectionWork)).click();
            return this;
        } else
        {
            wait.until(ExpectedConditions.elementToBeClickable(sectionServices)).click();
            return this;
        }
    }
    public ValtechPage verifySectionHeading(String expectedHeader){
        WebDriverWait wait = new WebDriverWait(driver, 15);

        final AutoCloseableSoftAssertions soft
                = new AutoCloseableSoftAssertions();
       if(expectedHeader.equals("ABOUT")){
           wait.until(ExpectedConditions.visibilityOf(sectionAbout));
           String actualAboutHeader = headerAbout.getText();
           soft.assertThat(actualAboutHeader);
           return this;

       }
        else if (expectedHeader.equals("WORK")){
           wait.until(ExpectedConditions.visibilityOf(sectionWork)).click();
           String actualWorkHeader = headerWork.getText();
           soft.assertThat(actualWorkHeader);
           return this;
       } else
       {
           wait.until(ExpectedConditions.visibilityOf(sectionServices)).click();
           String actualServiceHeader = headerServices.getText();
           soft.assertThat(actualServiceHeader);
           return this;
       }

    }
    public ValtechPage clickOnContactPageIcon(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(contactIcon)).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
    /*This verifyCountOfContactLocations method writes count of contact locations to testconfig.properties file*/
    public ValtechPage verifyCountOfContactLocations(){
        List<WebElement> locationImageList = driver.findElements(By.xpath("//i[@class='office__map__icon glyph']"));
        int locationsCount = locationImageList.size();
        PropertiesConfiguration config = null;
        ValtechPropertyFileReader valtechPropertyFileReader;
        try {
            config = new PropertiesConfiguration(TEST_CONFIG_PROP_FILE);
            config.setProperty("Contact_Locations_Count", locationsCount);
            config.save();

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
       assertEquals("40", ValtechPropertyFileReader.getLocationsCount());
        return this;
    }
}
