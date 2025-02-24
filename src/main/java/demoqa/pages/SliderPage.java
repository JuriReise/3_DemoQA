package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class SliderPage extends BasePage {
    public SliderPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(className = "range-slider")
    WebElement sliderContainer;

    @FindBy(id="sliderValue")
    WebElement sliderValue;

    public SliderPage moveSlider(Integer targetValue) {
        int min = Integer.parseInt(Objects.requireNonNull(sliderContainer.getDomAttribute("min")));
        int max = Integer.parseInt(Objects.requireNonNull(sliderContainer.getDomProperty("max")));
        if (targetValue == null || targetValue < min || targetValue > max) {
            throw new IllegalArgumentException(
                    String.format("Invalid target value: %d. Expected range: [%d to %d]", targetValue, min, max)
            );
        }
        // Кликаем по слайдеру
        click(sliderContainer);

        // Переменная, хранит текущее значение слайдера
        int currentValue = Integer.parseInt(Objects.requireNonNull(sliderContainer.getDomProperty("value")));

        // Разница между текущим значением и заданным
        int difference =targetValue - currentValue;

        try {
            // Используем класс Robot для имитации событий клавиатуры
            Robot robot = new Robot();

            // Если значения равны - идём дальше ничего не предпринимая
            if (currentValue == targetValue) {
                return this;
            }
            // Куда двигаться: влево или вправо
            int key = difference > 0 ? KeyEvent.VK_RIGHT : KeyEvent.VK_LEFT;

            // Выполняем движение
            for (int i = 0; i < Math.abs(difference); i++) {
                int beforeMove = Integer.parseInt(Objects.requireNonNull(sliderValue.getDomProperty("value")));
                System.out.println("Step # " + (i+1) + ", value: [" + beforeMove + "]");
                robot.keyPress(key);
                robot.keyRelease(key);
            }
            wait.until(ExpectedConditions.attributeToBe(sliderValue, "value", String.valueOf(targetValue)));
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public SliderPage verifySliderValue(int expectedValue) {
        String actual = sliderValue.getAttribute("value");
        String expected = String.valueOf(expectedValue);
        Assert.assertEquals(actual, expected);
        return this;
    }

    @FindBy(css = "input[type='range']")
    WebElement slider;

    public SliderPage moveSliderWithJS(Integer targetValue) {
        if (targetValue == null || targetValue < 0 || targetValue > 100) {
            throw new IllegalArgumentException("Target value must be between 0 and 100. Given: " + targetValue);
        }

        try {
            String script = "arguments[0].value = arguments[1]; " +
                    "arguments[0].dispatchEvent(new Event('input')); " +
                    "arguments[0].dispatchEvent(new Event('change'));" +
                    "document.getElementById('sliderValue').value = arguments[1]";

            ((JavascriptExecutor) driver).executeScript(script, slider, targetValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.attributeToBe(sliderValue, "value", String.valueOf(targetValue)));
        return this;
    }
}
