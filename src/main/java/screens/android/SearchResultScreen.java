package screens.android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import screens.AndroidBaseScreen;
import screens.android.data.ColorDepth;

import java.util.List;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class SearchResultScreen extends AndroidBaseScreen {

    private int maxLoops = 20;
    private int waitInterval = 0500;
    private boolean isGridView = true;

    @AndroidFindBy(id = "com.vdbapp.android:id/refine_main_button")
    private AndroidElement refineSearchHeader;

    @AndroidFindBy(id = "com.vdbapp.android:id/search_result_image")
    private List<AndroidElement> searchResultImages;

    @AndroidFindBy(id = "com.vdbapp.android:id/favorite_image")
    private List<AndroidElement> favoriteButtonsGridView;

    @AndroidFindBy(id = "com.vdbapp.android:id/favorite_menu_icon")
    private AndroidElement favoriteMenuButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/search_result_description")
    private List<AndroidElement> searchResultDescriptions;

    @AndroidFindBy(id = "com.vdbapp.android:id/list_button")
    private AndroidElement listButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/grid_button")
    private AndroidElement gridButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/favorite_button")
    private List<AndroidElement> favoriteButtonsListView;

    @AndroidFindBy(id = "com.vdbapp.android:id/diamond_title")
    private List<AndroidElement> diamondTitlesListView;

    @AndroidFindBy(id = "com.vdbapp.android:id/view_button")
    private List<AndroidElement> listViewDiamondButtons;

    @AndroidFindBy(id = "com.vdbapp.android:id/refine_color")
    private AndroidElement refineColorButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/inner_refine_fancy_color_button")
    private AndroidElement refineFancyColorButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/selectable_custom_button\n")
    private AndroidElement fancyBlueButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/selectable_custom_button")
    private List<AndroidElement> colorDepthButtons;

    @AndroidFindBy(id = "com.vdbapp.android:id/refine_done_button")
    private AndroidElement refineDoneButton;

    public SearchResultScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public AndroidElement getRefineSearchHeader() {
        return refineSearchHeader;
    }

    public List<AndroidElement> getSearchResultImages() {
        return searchResultImages;
    }

    public List<AndroidElement> getFavoriteButtonsGridView() {
        return favoriteButtonsGridView;
    }

    public AndroidElement getFavoriteMenuButton() {
        return favoriteMenuButton;
    }

    public List<AndroidElement> getSearchResultDescriptions() {
        return searchResultDescriptions;
    }

    public List<AndroidElement> getFavoriteButtonsListView() {
        return favoriteButtonsListView;
    }

    public List<AndroidElement> getDiamondTitlesListView() {
        return diamondTitlesListView;
    }

    public boolean isGridView() {
        return isGridView;
    }

    public AndroidElement getListButton() {
        return listButton;
    }

    public AndroidElement getGridButton() {
        return gridButton;
    }

    public List<AndroidElement> getListViewDiamondButtons() {
        return listViewDiamondButtons;
    }

    public AndroidElement getRefineColorButton() {
        return refineColorButton;
    }

    public AndroidElement getRefineFancyColorButton() {
        return refineFancyColorButton;
    }

    public AndroidElement getFancyBlueButton() {
        return fancyBlueButton;
    }

    public AndroidElement getRefineDoneButton() {
        return refineDoneButton;
    }

    public void clickColorDepth(ColorDepth colorDepth)
    {
        boolean found = false;
        for(RemoteWebElement e : colorDepthButtons)
        {
            if(e.getText().contains(colorDepth.getName())) e.click();
        }
        if(!found)
        {
            System.out.println("Color depth button not found!");
        }
    }

    public void waitForResultsToLoad() throws InterruptedException
    {
        int i = 0;

        while(i < maxLoops)
        {
            if((i + 1) == maxLoops) System.out.println("Last loop to load results! Results may have timed out!");
            Thread.sleep(waitInterval);
            i++;
            try
            {
                // The elements are different in Grid vs List view
                if(isGridView) Assert.assertTrue(searchResultDescriptions.size() >= 1);
                else Assert.assertTrue(diamondTitlesListView.size() >= 1);
                Thread.sleep(waitInterval);
                break;
            }
            catch (AssertionFailedError e) { System.out.println("Waiting for results to load"); }
        }
    }

    public void tapGridView() {
        gridButton.click();
        isGridView = true;
    }

    public void tapListView() {
        listButton.click();
        isGridView = false;
    }
}
