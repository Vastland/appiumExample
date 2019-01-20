package com.appium.maven.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;

/**
 * Test class for browser
 */
public class BrowserTest {

    // Instance of WebDriver
    private WebDriver driver;

    // Instance of WebDriverWait
    private WebDriverWait wait;

    //
    // WebElements
    //
    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchInput;

    /**
     * Set up method
     *
     * @throws IOException
     */
    @Before
    public void setUp() throws IOException {

        // Capabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        // Set device type
        desiredCapabilities.setCapability("device", "Android");

        // Set browser type. Leave it empty if it's app
        desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");

        // Set name of the device
        desiredCapabilities.setCapability("deviceName", "MI 5");

        // Set platform name
        desiredCapabilities.setCapability("platformName", "Android");

        // Initialize driver
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, 10);

        // Initialize WebElements
        PageFactory.initElements(driver, this);
    }

    /**
     * Open Chrome, go to Google and send 'GlobalLogic' to the search
     */
    @Test
    public void testBrowserTest() {
        driver.get("https://google.com.ua");
        searchInput.sendKeys("QA StandUp");
        searchInput.sendKeys(Keys.ENTER);
    }

    /**
     * Tear down method. Quit driver
     */
    @After
    public void tearDown() {
        driver.quit();
    }
}
