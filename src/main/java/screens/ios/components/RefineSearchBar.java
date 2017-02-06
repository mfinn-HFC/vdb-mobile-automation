package screens.ios.components;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import screens.IOSBaseScreen;

/**
 * Created by matt-hfc on 1/20/17.
 */
public class RefineSearchBar extends IOSBaseScreen {

    @iOSFindBy(id = "Budget")
    private IOSElement budgetButton;

    @iOSFindBy(id = "Shape")
    private IOSElement shapeButton;

    @iOSFindBy(id = "Carat")
    private IOSElement caratButton;

    @iOSFindBy(id = "Color")
    private IOSElement colorButton;

    @iOSFindBy(id = "Clarity")
    private IOSElement clarityButton;

    @iOSFindBy(id = "More...")
    private IOSElement moreButton;

    @iOSFindBy(id = "Polish/Symmetry")
    private IOSElement polishSymmetryButton;

    @iOSFindBy(id = "Lab")
    private IOSElement labButton;

    @iOSFindBy(id = "Enhancement")
    private IOSElement enhancementButton;

    @iOSFindBy(id = "Table/Depth")
    private IOSElement tableDepthButton;

    @iOSFindBy(id = "Fluorescence")
    private IOSElement fluorescenceButton;

    @iOSFindBy(id = "Less...")
    private IOSElement lessButton;

    @iOSFindBy(id = "Refine")
    private IOSElement refineButton;

    public RefineSearchBar(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public IOSElement getBudgetButton() {
        return budgetButton;
    }

    public IOSElement getLessButton() {
        return lessButton;
    }

    public IOSElement getTableDepthButton() {
        return tableDepthButton;
    }

    public IOSElement getFluorescenceButton() {
        return fluorescenceButton;
    }

    public IOSElement getEnhancementButton() {
        return enhancementButton;
    }

    public IOSElement getLabButton() {
        return labButton;
    }

    public IOSElement getPolishSymmetryButton() {
        return polishSymmetryButton;
    }

    public IOSElement getMoreButton() {
        return moreButton;
    }

    public IOSElement getClarityButton() {
        return clarityButton;
    }

    public IOSElement getColorButton() {
        return colorButton;
    }

    public IOSElement getCaratButton() {
        return caratButton;
    }

    public IOSElement getShapeButton() {
        return shapeButton;
    }

    public IOSElement getRefineButton() {
        return refineButton;
    }
}
