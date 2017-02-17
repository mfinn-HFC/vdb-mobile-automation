package android.smoke;

import base.AndroidBaseTest;
import io.appium.java_client.TouchAction;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import screens.android.DiamondViewScreen;
import screens.android.SearchResultScreen;
import screens.android.SearchScreen;
import screens.android.data.ColorDepth;
import screens.android.data.RotaryButton;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class ColorSearchTest extends AndroidBaseTest {

    private final String color = "D";

    public ColorSearchTest(DesiredCapabilities capabilities) {
        super(capabilities);
    }

    @Test
    public void colorSearchTest() throws InterruptedException {
        waitForBugReportPromptToClose();

        loginScreen.defaultLogin();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextID)));

        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickRotaryButton(RotaryButton.COLOR);
        SearchResultScreen searchResultScreen = searchScreen.tapSearchButton();
        searchResultScreen.waitForResultsToLoad();
        searchResultScreen.getListButton().click();
        RemoteWebElement viewButton = searchResultScreen.getListViewDiamondButtons().get(0);
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(viewButton).release().perform();

        DiamondViewScreen diamondViewScreen = new DiamondViewScreen(driver);
        wait.until(ExpectedConditions.visibilityOf(diamondViewScreen.getBackButton()));
        Assert.assertTrue(diamondViewScreen.getColorParentElement().findElements(By.id(color)).size() > 0);
        eyes.checkWindow(color + " Color Diamond");
        diamondViewScreen.getBackButton().click();

        searchResultScreen.getRefineSearchHeader().click();
        searchResultScreen.getRefineFancyColorButton().click();
        searchResultScreen.getFancyBlueButton().click();
        searchResultScreen.clickColorDepth(ColorDepth.VERY_LIGHT);
        searchResultScreen.getRefineDoneButton().click();
    }
}
