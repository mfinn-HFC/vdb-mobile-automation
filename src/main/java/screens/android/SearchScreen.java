package screens.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import screens.AndroidBaseScreen;
import screens.android.data.RotaryButton;

import java.util.List;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class SearchScreen extends AndroidBaseScreen {

    @AndroidFindBy(id = "com.vdbapp.android:id/search_icon")
    private AndroidElement searchButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/interval_from_tv")
    private AndroidElement tapToTypeTop;

    @AndroidFindBy(id = "com.vdbapp.android:id/interval_to_tv")
    private AndroidElement tapToTypeBottom;

    @AndroidFindBy(id = "com.vdbapp.android:id/interval_from")
    private AndroidElement editFromPriceField;

    @AndroidFindBy(id = "com.vdbapp.android:id/interval_to")
    private AndroidElement editToPriceField;

    @AndroidFindBy(id = "com.vdbapp.android:id/lab_button")
    private AndroidElement labButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/cert_number_edit")
    private AndroidElement certNumberField;

    @AndroidFindBy(className = "android.widget.ImageButton")
    private AndroidElement hamburgerMenuButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/drawer_item_collections")
    private AndroidElement collectionsHamburgerOption;

    @AndroidFindBy(id = "com.vdbapp.android:id/rotary_button")
    private List<AndroidElement> rotaryButtons;

    public SearchScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public AndroidElement getSearchButton() {
        return searchButton;
    }

    public SearchResultScreen tapSearchButton() {
        searchButton.click();
        return new SearchResultScreen(driver);
    }

    public AndroidElement getTapToTypeTop() {
        return tapToTypeTop;
    }

    public AndroidElement getTapToTypeBottom() {
        return tapToTypeBottom;
    }

    public AndroidElement getEditFromPriceField() {
        return editFromPriceField;
    }

    public AndroidElement getEditToPriceField() {
        return editToPriceField;
    }

    public AndroidElement getLabButton() {
        return labButton;
    }

    public AndroidElement getCertNumberField() {
        return certNumberField;
    }

    public AndroidElement getHamburgerMenuButton() {
        return hamburgerMenuButton;
    }

    public AndroidElement getCollectionsHamburgerOption() {
        return collectionsHamburgerOption;
    }

    public void clickRotaryButton(RotaryButton button) {
        boolean found = false;
        for(RemoteWebElement e : rotaryButtons)
        {
            if(e.getText().contains(button.getName())) e.click();
        }
        if(!found)
        {
            System.out.println("Rotary button not found!");
        }
    }
}
