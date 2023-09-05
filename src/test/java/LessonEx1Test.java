import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class LessonEx1Test {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","P11");
        capabilities.setCapability("platformVersion","11");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/morera/Desktop/JavaAppium/JavaAppium/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }
    @Test
    public void testElementHasText() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                10
        );

        assertElementHasText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']"),
                "Search Wikipedia",
                "Message is different than expected"
        );

    }

    private void assertElementHasText(By by, String expected, String error_message) {
        WebElement element = driver.findElement(by);
        String element_text = element.getText();
        Assert.assertEquals(
                error_message,
                expected,
                element_text
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

}
