package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SidePanel extends BasePage {
    public SidePanel(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    //* Login
    @FindBy(xpath = "//span[contains(text(),'Login')]")
    WebElement login;

    public LoginPage selectLogin() {
        click(login);
        return new LoginPage(driver, wait);
    }
}
