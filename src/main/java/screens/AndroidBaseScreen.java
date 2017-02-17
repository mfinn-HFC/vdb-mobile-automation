package screens;

import enums.SwipeDirection;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import model.BaseObject;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class AndroidBaseScreen extends BaseObject {

    protected final int maxLoops = 10;
    protected final int retryMax = 3;
    protected final int waitTime = 0500;
    protected AndroidDriver driver;

    public AndroidBaseScreen(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void hideKeyboard() {
        try
        {
            driver.hideKeyboard();
        }
        catch (WebDriverException ex) { driver.pressKeyCode(66); }
        catch (Exception ex) { /** If keyboard isn't visible **/ }
    }

    public void waitForElement(AndroidElement e) throws InterruptedException {
        for(int i = 0; i < maxLoops; i++)
        {
            try {
                if(!e.isDisplayed())
                {
                    Assert.assertTrue(e.isDisplayed());
                    Thread.sleep(waitTime);
                }
            }
            catch (WebDriverException ex)
            {
                System.out.println("Element not found, try number: " + i);
                Thread.sleep(waitTime);
            }
        }
    }

    public void fillField(AndroidElement e, String s) throws InterruptedException {
        int n = 0;
        while (n < retryMax)
        try
        {
            if(e.isDisplayed())
            {
                if(!e.getText().contains(s)) e.sendKeys(s);
                break;
            }
            // First do a small swipe
            swipeByPercent(3, SwipeDirection.DOWN);
            Thread.sleep(waitTime);
            e.sendKeys(s);
            break;
        }
        catch (NoSuchElementException ex)
        {
            try
            {
                swipeByPercent(3, SwipeDirection.DOWN);
                Thread.sleep(waitTime);
                e.sendKeys(s);
                break;
            }
            catch (WebDriverException exception)
            {
                System.out.println("Was not able to type into element. Try: " + n);
                hideKeyboard();
                n++;
            }
        }
    }
}
