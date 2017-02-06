package android.login;

import base.AndroidBaseTest;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.android.SearchScreen;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class LoginTest extends AndroidBaseTest {

    @Test(dataProvider = "drivers")
    public void loginTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        waitForBugReportPromptToClose();

        loginScreen.defaultLogin();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextID)));

        SearchScreen searchScreen = new SearchScreen(driver);
        Assert.assertTrue(searchScreen.getSearchButton().isDisplayed());
    }
}
