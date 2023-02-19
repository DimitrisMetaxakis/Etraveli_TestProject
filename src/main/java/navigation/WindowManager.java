package navigation;


import org.openqa.selenium.WebDriver;
import utilities.BaseActions;

/**
 * Class contains browser window actions
 * */
public class WindowManager extends BaseActions {

    private WebDriver driver;
    private WebDriver.Navigation navigate;



    /** Click go back from browser */
    public void goBack() {
        navigate.back();
    }

    /** Click go forward from browser */
    public void goForward() {
        navigate.forward();
    }

    /** Click refresh page */
    public void refreshPage() {
        navigate.refresh();
    }


}

