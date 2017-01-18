package screens.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import screens.AndroidBaseScreen;

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

    public SearchScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public AndroidElement getSearchButton() {
        return searchButton;
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
}
