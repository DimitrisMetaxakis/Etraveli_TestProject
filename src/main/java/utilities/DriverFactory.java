package utilities;

import org.openqa.selenium.WebDriver;

/**
 *
 * Implement Singleton design pattern pass same instance of the driver across session pages
 *
 * This class provides methods for managing a thread-safe WebDriver object, which is used to automate web browser interactions.
 * By using the ThreadLocal class to manage the WebDriver object, this class ensures that each thread has its own instance of the WebDriver object,
 * which helps to prevent issues with concurrency and thread-safety in multi-threaded environments.
 * The getter and setter methods allow other classes to access and modify the WebDriver object for the current thread.
 */
public class DriverFactory {

    // Thread safe Webdriver
    // Creates a static, thread-local variable called webDriver, which will store a WebDriver object for each thread that uses it.
    // The ThreadLocal class provides a way to create variables that are unique to each thread and are not shared between threads.
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    /**
     * Getter for driver
     * */
    public static WebDriver getWebDriver(){
        return webDriver.get();
    }

    /**
     * Setter for driver
     * */
    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

}