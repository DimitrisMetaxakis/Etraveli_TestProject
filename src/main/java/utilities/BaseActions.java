package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Class contains
 * -Basic Methods
 */
public class BaseActions {
    // Timeout value for explicit waits in seconds
    private final int waitForElementTimeout = 15;

    /////////////////////// Basic Methods ///////////////////////

    /**
     * Wait for Element Not Visible
     *
     * @param locator
     */

    public void waitForElementNotVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), Duration.ofSeconds(waitForElementTimeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }


    /**
     * Wait for Element
     *
     * @param locator
     */

    public void waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), Duration.ofSeconds(waitForElementTimeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for Element to be clickable
     *
     * @param locator
     */

    public void waitForElementIsClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), Duration.ofSeconds(waitForElementTimeout));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Get text
     *
     * @param locator
     */
    public String getText(By locator) {
        return DriverFactory.getWebDriver().findElement(locator).getText().trim();
    }

    /**
     * Click element
     *
     * @param locator
     */
    public void click(By locator) {
        DriverFactory.getWebDriver().findElement(locator).click();
    }

    /**
     * Click element
     *
     * @param locator
     */
    public void clickJS(By locator) {
        WebElement m=DriverFactory.getWebDriver().findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getWebDriver();
        js.executeScript("arguments[0].click();", m);
    }


    /**
     * Clears field
     * Writes field
     * @param locator
     */
    public void sendKeys(By locator, String text) {
        DriverFactory.getWebDriver().findElement(locator).clear();
        DriverFactory.getWebDriver().findElement(locator).sendKeys(text);
    }

    /**
     * Click Enter
     * */
    public void clickEnter(By locator){
        DriverFactory.getWebDriver().findElement(locator).sendKeys(Keys.ENTER);
    }

    /**
     * Scroll at the bottom of the page with Javascript
     */
    public void scrollBottomPageJS() {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getWebDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }


    /**
     * Returns List of Web Elements that Match locator
     */
    public List<WebElement> findElements(By locator){
       return DriverFactory.getWebDriver().findElements(locator);
    }


    /**
     * Slide Element
     */
    public void slideElement(By locator,int xOffset, int yOffset){
        WebElement slider = DriverFactory.getWebDriver().findElement(locator);
        Actions action = new Actions(DriverFactory.getWebDriver());
        action.dragAndDropBy(slider,xOffset,yOffset).perform();

    }

}
