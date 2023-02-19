package base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.FiltersPO;
import pageObjects.HomePagePO;
import pageObjects.ResultsPO;
import utilities.DriverFactory;

import java.time.Duration;

/**
 * Class contains logic that is executed before and after every test class
 */
public class BaseTest {

    WebDriver driver;

    protected HomePagePO homepage;
    protected ResultsPO results;
    protected FiltersPO filters;

    /**
     * Initialize browser
     * Initialize Implicit Wait
     * @param hostUrl provided in testng.xml
     * @param browser provided in testng.xml
     * @param timeout provided in testng.xml
     */
    @Parameters({"hostUrl", "browser", "timeout"})
    @BeforeClass
    public void setUp(String hostUrl, String browser, int timeout) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        // Go to hostUrl page
        driver.get(hostUrl);
        // Maximize window to reduce flakiness
        driver.manage().window().maximize();
        // Pass driver to DriverFactory to delegate responsibility
        DriverFactory.setWebDriver(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

        homepage = new HomePagePO();
        results = new ResultsPO();
        filters = new FiltersPO();
    }

    /**
     * Close browser
     */
    @AfterClass
    public void tearDown() {
        DriverFactory.getWebDriver().quit();
    }

}
