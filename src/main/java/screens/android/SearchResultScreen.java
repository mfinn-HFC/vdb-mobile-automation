package screens.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import screens.AndroidBaseScreen;

import java.util.List;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class SearchResultScreen extends AndroidBaseScreen {

    @AndroidFindBy(id = "com.vdbapp.android:id/refine_main_button")
    private AndroidElement refineSearchHeader;

    @AndroidFindBy(id = "com.vdbapp.android:id/search_result_image")
    private List<AndroidElement> searchResultImages;

    @AndroidFindBy(id = "com.vdbapp.android:id/favorite_image")
    private List<AndroidElement> favoriteButtons;

    @AndroidFindBy(id = "com.vdbapp.android:id/favorite_menu_icon")
    private AndroidElement favoriteMenuButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/search_result_description")
    private List<AndroidElement> searchResultDescriptions;

    public SearchResultScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public AndroidElement getRefineSearchHeader() {
        return refineSearchHeader;
    }

    public List<AndroidElement> getSearchResultImages() {
        return searchResultImages;
    }

    public List<AndroidElement> getFavoriteButtons() {
        return favoriteButtons;
    }

    public AndroidElement getFavoriteMenuButton() {
        return favoriteMenuButton;
    }

    public List<AndroidElement> getSearchResultDescriptions() {
        return searchResultDescriptions;
    }
}
