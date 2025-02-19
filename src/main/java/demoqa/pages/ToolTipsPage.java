package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToolTipsPage extends BasePage {
    public ToolTipsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//a[.='Contrary']")
    WebElement contrary;

    public ToolTipsPage hoverToolTip() {
        scrollTo(500);
        //! .perform(); обязательно в конце команды
        pause(1000);
        new Actions(driver).moveToElement(contrary).perform();
        return this;
    }

    @FindBy(className = "tooltip-inner")
    WebElement tooltipInner;

    public ToolTipsPage verifyToolTipsText(String text) {
        shouldHaveText(tooltipInner, text, 5000);
        return this;
    }
}
