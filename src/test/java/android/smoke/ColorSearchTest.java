package android.smoke;

import base.AndroidBaseTest;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
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
    private final String fancyColor = "DP OR";

    @Test(dataProvider = "drivers")
    public void colorSearchTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        waitForBugReportPromptToClose();

        loginScreen.defaultLogin();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextID)));

        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.getRotaryButton(RotaryButton.COLOR).click();
        driver.findElement(By.id(color)).click();
        SearchResultScreen searchResultScreen = searchScreen.tapSearchButton();
        searchResultScreen.waitForResultsToLoad();
        searchResultScreen.getListButton().click();
        searchResultScreen.getListViewDiamondButtons().get(0).click();

        DiamondViewScreen diamondViewScreen = new DiamondViewScreen(driver);
        Assert.assertTrue(diamondViewScreen.getColorParentElement().findElements(By.id(color)).size() > 0);
        eyes.checkWindow(color + " Color Diamond");
        diamondViewScreen.getBackButton().click();

        searchResultScreen.getRefineSearchHeader().click();
        searchResultScreen.getRefineFancyColorButton().click();
        searchResultScreen.getFancyBlueButton().click();
        searchResultScreen.getColorDepth(ColorDepth.VERY_LIGHT).click();
        searchResultScreen.getRefineDoneButton().click();
    }
}
