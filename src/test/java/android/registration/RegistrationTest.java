package android.registration;

import base.AndroidBaseTest;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.android.RegistrationScreen;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class RegistrationTest extends AndroidBaseTest {

    @Test(dataProvider = "drivers")
    public void registrationTest(AndroidDriver driver) throws InterruptedException {
        setUp(driver);
        waitForBugReportPromptToClose();

        loginScreen.getSignUpButton().click();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);

        registrationScreen.fillRegistrationForm(generateEmail());
        registrationScreen.getSignUpButton().click();

        wait.until(ExpectedConditions.visibilityOf(loginScreen.getUsernameEditText()));
        Assert.assertTrue(driver.findElement(By.name("Thank you!")).isDisplayed());
    }
}