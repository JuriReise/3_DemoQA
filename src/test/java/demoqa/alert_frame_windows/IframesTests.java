package demoqa.alert_frame_windows;

import demoqa.core.TestBase;
import demoqa.pages.FramesPage;
import demoqa.pages.HomePage;
import demoqa.pages.SidePanel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IframesTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getAlertFrameWindows().hideAds();
        new SidePanel(app.driver, app.wait).selectFrames().hideAds();
    }

    @Test
    public void iframePositiveTest() {
        new FramesPage(app.driver, app.wait)
                .getListOfFrames();
    }

    @Test
    public void switchToIframeByIndexPositiveTest() {
        new FramesPage(app.driver, app.wait)
                .switchToIframeByIndex(2)
                .verifyIframeText("This is a sample page")
                ;
    }

    @Test
    public void switchToIframeByNamePositiveTest() {
        new FramesPage(app.driver, app.wait)
                .switchToIframeByName("frame1")
                .verifyIframeText("This is a sample page")
        ;
    }
}
