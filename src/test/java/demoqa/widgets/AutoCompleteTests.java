package demoqa.widgets;

import demoqa.core.TestBase;
import demoqa.pages.AutoCompletePage;
import demoqa.pages.HomePage;
import demoqa.pages.SidePanel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutoCompleteTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getWidgets().hideAds();
        new SidePanel(app.driver, app.wait).selectAutoCompleteMenu().hideAds();
    }

    @Test
    public void autoCompleteSinglePositiveTest() {
        new AutoCompletePage(app.driver, app.wait)
               .autoComplete("M")
               .verifyAutoComplete("Magenta")
        ;
    }


    @Test
    public void autoCompleteArrayPositiveTest() {
        String[] autoCompleteArray = {"m","b","g"};
        String[] verifyAutoCompleteArray = {"Magenta","Blue","Green"};
        new AutoCompletePage(app.driver, app.wait)
                .autoCompleteArray(autoCompleteArray)
                .verifyAutoCompleteArray(verifyAutoCompleteArray)
        ;
    }
}
