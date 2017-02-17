package android.login;

import base.AndroidBaseTest;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import screens.android.SearchScreen;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class LoginTest extends AndroidBaseTest {

    public LoginTest(DesiredCapabilities capabilities) {
        super(capabilities);
    }

    @Test
    public void loginTest() throws InterruptedException {
        waitForBugReportPromptToClose();

        loginScreen.defaultLogin();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextID)));

        SearchScreen searchScreen = new SearchScreen(driver);
        Assert.assertTrue(searchScreen.getSearchButton().isDisplayed());
    }
}
