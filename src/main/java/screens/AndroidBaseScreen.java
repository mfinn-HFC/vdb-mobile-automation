package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class AndroidBaseScreen {

    protected final int maxLoops = 10;
    protected final int waitTime = 0500;

    protected AndroidDriver driver;

    public void hideKeyboard() {
        try
        {
            driver.hideKeyboard();
        }
        catch (WebDriverException ex) { driver.pressKeyCode(66); }
        catch (Exception ex) { /** If keyboard isn't visible **/ }
    }

    public void waitForElement(AndroidElement e)
    {
        for(int i = 0; i < maxLoops; i++)
        {
            try
            {
                Thread.sleep(waitTime);
                Assert.assertTrue(e.isDisplayed());
                Thread.sleep(waitTime);
            }
            catch (WebDriverException | InterruptedException ex)
            {
                System.out.println("Element not found, try number: " + i);
            }
        }
    }

}
