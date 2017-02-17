package android.favorite;

import base.AndroidBaseTest;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import screens.android.*;
import screens.android.DiamondViewScreen;
import screens.android.SearchScreen;
import screens.android.TermsAndConditionsScreen;


/**
 * Created by matt-hfc on 12/14/16.
 */
public class FavoriteFromDiamondDetailTest extends AndroidBaseTest {

    private final String mp4DiamondCertNumber = "5556664536";
    private final String mp4DiamondTitle = "PS 4.05 D SI2";

    public FavoriteFromDiamondDetailTest(DesiredCapabilities capabilities) {
        super(capabilities);
    }

    @Test
    public void favoriteFromDiamondViewTest() throws InterruptedException {
        waitForBugReportPromptToClose();
        registerActivateNewUser();

        TermsAndConditionsScreen termsAndConditionsScreen = loginScreen.loginNewUser(user);
        SearchScreen searchScreen = termsAndConditionsScreen.acceptTerms();
        wait.until(ExpectedConditions.visibilityOf(searchScreen.getLabButton()));
        searchScreen.getLabButton().click();
        searchScreen.getCertNumberField().sendKeys(mp4DiamondCertNumber);

        // Need to hit 'Search' key on keyboard
        driver.pressKeyCode(AndroidKeyCode.ENTER);

        DiamondViewScreen diamondViewScreen = new DiamondViewScreen(driver);
        diamondViewScreen.waitForElement(diamondViewScreen.getFavoriteButton());
        diamondViewScreen.getFavoriteButton().click();
        // TODO eyes check here
        diamondViewScreen.getBackButton().click();
        // TODO eyes check here

        searchScreen.waitForElement(searchScreen.getHamburgerMenuButton());
        searchScreen.getHamburgerMenuButton().click();
        searchScreen.getCollectionsHamburgerOption().click();
        FavoriteScreen favoriteScreen = new FavoriteScreen(driver);
        Assert.assertTrue(favoriteScreen.getLovedCheckboxes().size() == 1);

        boolean titleIsPresent = false;
        for(RemoteWebElement e : favoriteScreen.getDiamondDescriptions())
        {
            if(e.getText().contains(mp4DiamondTitle)) titleIsPresent = true;
        }
        Assert.assertTrue(titleIsPresent);
    }
}