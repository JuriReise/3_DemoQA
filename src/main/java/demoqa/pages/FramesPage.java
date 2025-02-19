package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FramesPage extends BasePage {
    public FramesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(tagName = "iframe")
    List<WebElement> iframes;

    public FramesPage getListOfFrames() {
        int numberOfFrames = ((Long) js.executeScript("return window.length")).intValue();
        //System.out.println(numberOfFrames);
        if (iframes == null || iframes.isEmpty()){
            System.out.println("No iFrame was found using @FindBy");
            return this;
        }
        System.out.println("Number of iFrames on the page are: [" + numberOfFrames + "]");
        System.out.println("Number of iFrames on the page are: [" + iframes.size() + "]");

        for(WebElement iframe : iframes){
            String iFrameID = iframe.getAttribute("id");
            String iFrameSRC = iframe.getAttribute("src");
            System.out.println("Iframe found ID: [" + (iFrameID != null ? iFrameID : "No ID") + "], SRC: [" + (iFrameSRC != null ? iFrameSRC : "No SRC") + "]");
        }
        return this;
    }

    public FramesPage switchToIframeByIndex(int index) {
        driver.switchTo().frame(index);
        return this;
    }

    @FindBy(id = "sampleHeading")
    WebElement sampleHeading;

    public FramesPage verifyIframeText(String text) {
        shouldHaveText(sampleHeading,text, 5000);
        return this;
    }

    public FramesPage switchToIframeByName(String name) {
        //scrollTo(500);
        driver.switchTo().frame(name);
        return this;
    }
}
