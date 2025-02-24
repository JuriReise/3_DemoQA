package demoqa.core;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.js = (JavascriptExecutor) driver;
        // PageFactory упрощает работу с веб-элементами и делает код более чистым и читаемым
        // PageFactory — это утилита в Selenium WebDriver,
        // которая упрощает инициализацию веб-элементов на странице
        // PageFactory инициализирует элементы, помеченные аннотациями @FindBy в вашем классе страницы (this)
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        scrollToElement(element);
        element.click();
    }

    private void scrollToElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        waitForPageScrollToFinish();
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void typeWithJS(WebElement element, String text, int x, int y) {
        if (text != null) {
            js.executeScript("window.scrollBy(" + x + "," + y + ")");
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void clickWithJS(WebElement element, int x, int y) {
        // js.executeScript("window.scrollBy(100,200)");
        // x - сколько пикселей прокрутить по горизонтали
        // y - сколько пикселей прокрутить по вертикали
        //  js.executeScript("window.scrollBy(0,500)");
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
        //js.executeScript("window.scrollBy({},{})",x,y);
        click(element);
    }

    public void scrollTo(int y) {
        js.executeScript("window.scrollBy(0," + y + ")");
    }

    public void hideAds() {
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
        js.executeScript("document.querySelector('footer').style.display='none';");
    }

    public String takeScreenshot() {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("src/test_screenshots/screen-" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Screenshot saved to: [" + screenshot.getAbsolutePath() + "]");
        return screenshot.getAbsolutePath();
    }

    protected void shouldHaveText(WebElement element, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeout));
        try {
            boolean isTextPresent = wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            Assert.assertTrue(isTextPresent, "Expected text: [" + text + "], actual text in element: [" + element.getText() + "] in element: [" + element + "]");
        } catch (TimeoutException e) {
            throw new AssertionError("Text not found in element: [" + element + "], expected text: [" + text + "] was text:[" + element.getText() + "]", e);
        }
    }

    public void pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitForPageScrollToFinish() {
        wait.until(driver -> {
            double beforeScroll, afterScroll;
            do {
                beforeScroll = ((Number) js.executeScript("return window.scrollY;")).doubleValue();
                pause(50); // Ждём короткий промежуток времени
                afterScroll = ((Number) js.executeScript("return window.scrollY;")).doubleValue();
            } while (beforeScroll != afterScroll); // Если скролл ещё идёт, повторяем
            return true; // Выходим из ожидания, когда прокрутка остановилась
        });
    }
}
