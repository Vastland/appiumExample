package com.qaopen.appium.tests;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Test class for app
 */
public class TestApp {

    // Instance of WebDriver
    private AndroidDriver driver;

    // Instance of WebDriverWait
    private WebDriverWait wait;

    // Test string - username
    private final String USER_NAME = "Alexey ";

    //
    // WebElements
    //
    @FindBy(xpath = ".//*[@resource-id='ua.com.rozetka.shop:id/menu_item_header_tv_sign_in']")
    private WebElement signInLink;

    @FindBy(xpath = ".//*[@resource-id='ua.com.rozetka.shop:id/sign_in_et_login']")
    private WebElement emailInput;

    @FindBy(xpath = ".//*[@resource-id='ua.com.rozetka.shop:id/sign_in_et_password']")
    private WebElement passwordInput;

    @FindBy(xpath = ".//*[@resource-id='ua.com.rozetka.shop:id/ll_background']")
    private WebElement signInButton;

    @FindBy(xpath = ".//*[@resource-id='ua.com.rozetka.shop:id/ll_search']")
    private WebElement searchInput;

    @FindBy(xpath = ".//*[@class='android.widget.ImageButton']")
    private WebElement hamburgerButton;

    @FindBy(xpath = ".//*[@resource-id='ua.com.rozetka.shop:id/menu_item_header_tv_name']")
    private WebElement userNameLink;

    /**
     * Set up method
     *
     * @throws IOException
     */
    @Before
    public void setUp() throws IOException {
        // Application file
        File app = new File("src/main/resources/Rozetka_v3.2.2.apk");

        // Capabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        // Set device type
        desiredCapabilities.setCapability("device", "Android");

        // Set browser type. Leave it empty if it's app
        desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, "");

        // Set path to app file
        desiredCapabilities.setCapability("app", app.getAbsolutePath());

        // Set name of the device
        desiredCapabilities.setCapability("deviceName", "MI 5");

        // Set platform name
        desiredCapabilities.setCapability("platformName", "Android");

        // Set package of the app
        desiredCapabilities.setCapability("appPackage", "ua.com.rozetka.shop");

        // Set activity of the app
        desiredCapabilities.setCapability("appActivity", "ua.com.rozetka.shop.ui.InitActivity");

        // Initialize driver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, 10);

        // Initialize WebElements
        PageFactory.initElements(driver, this);
    }

    /**
     * Open Rozetka app, log in and verify user name
     */
    @Test
    public void testTestApp() {
        waitTillClickableAndClick(signInLink);
        logIn();
        waitTillClickableAndClick(hamburgerButton);
        waitTillClickable(userNameLink);
        Assert.assertEquals("Name is not correct", USER_NAME, userNameLink.getText());
    }

    /**
     * Wait till element is clickable and click on it
     *
     * @param element
     */
    private void waitTillClickableAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * Wait till element is clickable
     *
     * @param element
     */
    private void waitTillClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Perform login
     */
    private void logIn() {
        waitTillClickable(emailInput);
        emailInput.sendKeys("g5444355@nwytg.net");
        if (driver.isKeyboardShown())
            driver.navigate().back();
        passwordInput.sendKeys("Temp123");
        if (driver.isKeyboardShown())
            driver.navigate().back();
        waitTillClickableAndClick(signInButton);
    }

    /**
     * Tear down method. Quit driver
     */
    @After
    public void tearDown() {
        driver.quit();
    }

}
