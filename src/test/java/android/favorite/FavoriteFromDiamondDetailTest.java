package android.favorite;

import base.AndroidBaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.android.*;
import screens.android.DiamondViewScreen;
import screens.android.SearchResultScreen;
import screens.android.SearchScreen;
import screens.android.TermsAndConditionsScreen;


/**
 * Created by matt-hfc on 12/14/16.
 */
public class FavoriteFromDiamondDetailTest extends AndroidBaseTest {

    private final String mp4DiamondCertNumber = "6241438392";
    private final String mp4DiamondTitle = "CU 1.41 F SI2";

    @Test(dataProvider = "drivers")
    public void favoriteFromDiamondViewTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        waitForBugReportPromptToClose();
        registerActivateNewUser();

        TermsAndConditionsScreen termsAndConditionsScreen = loginScreen.loginNewUser(user);
        SearchScreen searchScreen = termsAndConditionsScreen.acceptTerms();
        searchScreen.getLabButton().click();
        searchScreen.getCertNumberField().sendKeys(mp4DiamondCertNumber);
        // Hide keyboard triggers the search, no need to tap search button
        hideKeyboard();

        try {
            searchScreen.getSearchButton().click();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Click did not work - dismissKeyboard()" +
                    "may have triggered the search on its own");
        }

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
        Assert.assertTrue(favoriteScreen.getLovedCheckboxes().size() >= 1);

        boolean titleIsPresent = false;
        for(AndroidElement e : favoriteScreen.getDiamondDescriptions())
        {
            if(e.getText().contains(mp4DiamondTitle)) titleIsPresent = true;
        }
        Assert.assertTrue(titleIsPresent);
    }
}