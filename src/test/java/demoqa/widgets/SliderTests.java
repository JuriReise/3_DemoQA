package demoqa.widgets;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.SidePanel;
import demoqa.pages.SliderPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SliderTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getWidgets().hideAds();
        new SidePanel(app.driver, app.wait).selectSliderMenu().hideAds();
    }

    @Test
    public void moveSliderPositiveTest(){
        Integer setSlider = 10;
        new SliderPage(app.driver, app.wait)
                .moveSlider(setSlider)
                .moveSliderWithJS(setSlider)
                .verifySliderValue(setSlider);
    }
}
