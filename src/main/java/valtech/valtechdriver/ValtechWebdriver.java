package valtech.valtechdriver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import valtech.valtechenums.ValtechBrowser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.remote.DesiredCapabilities.chrome;
import static valtech.valtechconstants.ValtechConfigConstants.CHROME_DRIVER_PATH_KEY;
import static valtech.valtechconstants.ValtechConfigConstants.CHROME_DRIVER_PATH_VALUE;
import static valtech.valtechconstants.ValtechConfigConstants.GECKO_DRIVER_PATH_VALUE;


/**
 * Created by maheshboyalla on 16/01/2018.
 */
public class ValtechWebdriver {

    private WebDriver driver;

    public WebDriver getDriver(String browserType) {

        switch (ValtechBrowser.lookup(browserType)) {
            case Firefox:
                driver = getFirefoxDriver();
                browserWindowMaximize();
                break;
            case Chrome:
                driver = getChromeDriver();
                browserWindowMaximize();
                break;
            case IE:
                driver = getIEDriver();
                browserWindowMaximize();
                break;
            default:
                System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
                driver = getFirefoxDriver();
                browserWindowMaximize();

        }
        return driver;
    }

    public void browserWindowMaximize(){
        driver.manage().window().maximize();
    }

    private  WebDriver getFirefoxDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        System.setProperty("webdriver.gecko.driver", GECKO_DRIVER_PATH_VALUE);
        WebDriver driver = new FirefoxDriver(capabilities);
        return driver;
    }

    private  WebDriver getChromeDriver() {
        DesiredCapabilities cap = chrome();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--test-type");
        cap.setCapability("pageLoadStrategy", "none");
        cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        System.setProperty(CHROME_DRIVER_PATH_KEY, new File(CHROME_DRIVER_PATH_VALUE).getPath());
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    private  WebDriver getIEDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability(CapabilityType.
                ACCEPT_SSL_CERTS,true);
        capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
        capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        WebDriver driver = new InternetExplorerDriver(capabilities);

        return driver;
    }

    public void tearDown(){
        driver.quit();

    }

}
