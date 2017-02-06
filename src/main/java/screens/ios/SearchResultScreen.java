package screens.ios;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import screens.IOSBaseScreen;
import screens.ios.components.RefineSearchBar;

import java.util.List;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class SearchResultScreen extends IOSBaseScreen {

    private int maxLoops = 10;
    private int waitInterval = 0500;

    @iOSFindBy(id = "btn favorite idle")
    private List<IOSElement> favoriteButtonGridView;

    @iOSFindBy(id = "favorite toolbar on")
    private IOSElement favoriteToolbarButton;

    @iOSFindBy(id = "REFINE SEARCH")
    private IOSElement refineSearchButton;

    @iOSFindBy(id = "Budget")
    private IOSElement refineBudgetButton;

    @iOSFindBy(id = "900,000")
    private IOSElement maxBudgetField;

    @iOSFindBy(id = "0")
    private IOSElement minBudgetField;

    @iOSFindBy(id = "Done")
    private IOSElement refineDoneButton;

    @iOSFindBy(id = "page 1 of 21") // TODO: ask for name selector to resolve this issue
    private IOSElement resultTableParent;

    @iOSFindBy(id = "Back")
    private IOSElement backButton;

    @iOSFindBy(id = "icon listview")
    private IOSElement listviewButton;

    @iOSFindBy(id = "icon eye")
    private List<IOSElement> eyeIconButtons;

    @iOSFindBy(id = "Fancy RotaryButtons")
    private IOSElement fancyColorsButton;

    @iOSFindBy(id = "Orange")
    private IOSElement fancyOrangeColorButton;

    @iOSFindBy(id = "Deep")
    private IOSElement deepColorButton;

    @iOSFindBy(id = "favorite wholesale small")
    private List<IOSElement> favoriteButtonsListView;

    public SearchResultScreen(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public RefineSearchBar refineSearchBar() {
        return new RefineSearchBar(driver);
    }

    public List<IOSElement> getFavoriteButtonGridView() {
        return favoriteButtonGridView;
    }

    public IOSElement getFavoriteToolbarButton() {
        return favoriteToolbarButton;
    }

    public IOSElement getRefineSearchButton() {
        return refineSearchButton;
    }

    public IOSElement getRefineBudgetButton() {
        return refineBudgetButton;
    }

    public IOSElement getMaxBudgetField() {
        return maxBudgetField;
    }

    public IOSElement getRefineDoneButton() {
        return refineDoneButton;
    }

    public IOSElement getMinBudgetField() {
        return minBudgetField;
    }

    public IOSElement getResultTableParent() {
        return resultTableParent;
    }

    public IOSElement getListviewButton() {
        return listviewButton;
    }

    public List<IOSElement> getEyeIconButtons() {
        return eyeIconButtons;
    }

    public List<MobileElement> getResultListChildren() {
        return resultTableParent.findElements(By.className("UIATableCell"));
    }

    public IOSElement getFancyColorsButton() {
        return fancyColorsButton;
    }

    public IOSElement getBackButton() {
        return backButton;
    }

    public IOSElement getFancyOrangeColorButton() {
        return fancyOrangeColorButton;
    }

    public IOSElement getDeepColorButton() {
        return deepColorButton;
    }

    public List<IOSElement> getFavoriteButtonsListView() {
        return favoriteButtonsListView;
    }

    public void waitForSearchResultsToLoad() throws InterruptedException
    {
        int i = 0;

        while(i < maxLoops)
        {
            if((i + 1) == maxLoops) System.out.println("Last loop to load results! Results may have timed out!");
            Thread.sleep(waitInterval);
            i++;
            try
            {
                Assert.assertTrue(favoriteButtonGridView.size() >= 1);
                break;
            }
            catch (AssertionFailedError e) { System.out.println("Waiting for results to load"); }
        }
    }

    public void waitForListViewToLoad() throws InterruptedException
    {
        int i = 0;

        while(i < maxLoops)
        {
            if((i + 1) == maxLoops) System.out.println("Last loop to load results! Results may have timed out!");
            Thread.sleep(waitInterval);
            i++;
            try {
                Assert.assertTrue(favoriteButtonsListView.size() >= 1);
                break;
            } catch (NoSuchElementException e) { System.out.println("Waiting for list view to load"); }
        }
    }
}
