package pageObjects;

import enums.SlidersEnum;
import org.openqa.selenium.By;
import utilities.BaseActions;

public class FiltersPO extends BaseActions {

    private By clearButton = By.cssSelector("button[data-testid='filtersForm-resetFilters-button']");
    private By doneButton = By.cssSelector("button[data-testid='filtersForm-applyFilters-button']");
    private By filtersButton = By.cssSelector("button[data-testid='resultPage-toggleFiltersButton-button']");
    private By allFilters = By.cssSelector("div[data-testid='resultPage-searchFilters-content']");

    // airlines
    private By clearAllAirlinesButton = By.cssSelector("div[data-testid='resultPage-AIRLINESFilter-content'] span:nth-child(1)");
    private By selectAllAirlinesButton = By.cssSelector("div[data-testid='resultPage-AIRLINESFilter-content'] span:nth-child(2)");
    private By aegeanCheckbox = By.cssSelector("input#airlines-A3");

    // no of stops
    private By nonStopButton = By.cssSelector("label[data-testid='MAX_STOPS-direct']");
    private By maxOneStopButton = By.cssSelector("label[data-testid='MAX_STOPS-max1']");
    private By allStopsButton = By.cssSelector("label[data-testid='MAX_STOPS-all']");

    // departure - arrival times
    private By departureGoRadioButton = By.cssSelector("input#departure-0");
    private By departureReturnRadioButton = By.cssSelector("input#departure-1");
    private By arrivalGoRadioButton = By.cssSelector("input#arrival-0");
    private By arrivalReturnRadioButton = By.cssSelector("input[data-testid='resultPage-departureArrivalFilter-arrival1-radio']");
    private By goSliderButtonMin = By.cssSelector("div[data-testid='resultPage-departureArrivalFilter-departure0-slider'] div[data-testid='handle-0']");
    private By goSliderButtonMax = By.cssSelector("div[data-testid='resultPage-departureArrivalFilter-departure0-slider'] div[data-testid='handle-1']");
    private By returnSliderButtonMin = By.cssSelector("div[data-testid='resultPage-departureArrivalFilter-departure1-slider'] div[data-testid='handle-0']");
    private By returnSliderButtonMax = By.cssSelector("div[data-testid='resultPage-departureArrivalFilter-departure1-slider'] div[data-testid='handle-1']");

    // travel time
    private By travelTimeSliderButton = By.cssSelector("div[data-testid='resultPage-TRAVEL_TIMEFilter-content'] div[data-testid='handle-0']");

    //price
    private By priceSliderButtonMin = By.cssSelector("div[data-testid='resultPage-PRICEFilter-content'] div[data-testid='handle-0']");
    private By priceSliderButtonMax = By.cssSelector("div[data-testid='resultPage-PRICEFilter-content'] div[data-testid='handle-1']");


    /***
     * Clicks Filter Button and opens filter menu
     * @return
     */
    public FiltersPO clickFiltersButton() {
        waitForElementIsClickable(filtersButton);
        click(filtersButton);
        waitForElementVisible(allFilters);
        return this;
    }


    /***
     * Clicks Non Stop Flight Button at Filters
     * @return
     */
    public FiltersPO clickNonStopFlight() {
        waitForElementIsClickable(nonStopButton);
        click(nonStopButton);
        return this;
    }

    /***
     * Clicks All Stops Flight Button at Filters
     * @return
     */
    public FiltersPO clickAllStopsButton() {
        waitForElementIsClickable(allStopsButton);
        click(allStopsButton);
        return this;
    }

    /***
     * Clicks Max one stop Flight Button at Filters
     * @return
     */
    public FiltersPO clickMaxOneStopButton() {
        waitForElementIsClickable(maxOneStopButton);
        click(maxOneStopButton);
        return this;
    }

    /***
     * Clicks Clear All Airlines Button at Filters
     * @return
     */
    public FiltersPO clickClearAllAirlinesButton() {
        waitForElementIsClickable(clearAllAirlinesButton);
        click(clearAllAirlinesButton);
        return this;
    }

    /***
     * Clicks Select All Airlines Button at Filters
     * @return
     */
    public FiltersPO clickSelectAllAirlinesButton() {
        waitForElementIsClickable(selectAllAirlinesButton);
        click(selectAllAirlinesButton);
        return this;
    }

    /***
     * Clicks Aegean Airlines Checkbox at Filters
     * @return
     */
    public FiltersPO clickAegeanAirlinesCheckbox() {
        click(aegeanCheckbox);
        return this;
    }

    /***
     * Clicks Arrival Go Radio Button at Filters
     * @return
     */
    public FiltersPO clickArrivalGoRadioButton() {
        waitForElementIsClickable(arrivalGoRadioButton);
        click(arrivalGoRadioButton);
        return this;
    }

    /***
     * Clicks Arrival Return Radio Button at Filters
     * @return
     */
    public FiltersPO clickArrivalReturnRadioButton() {
        clickJS(arrivalReturnRadioButton);
        return this;
    }

    /***
     * Clicks Clear Button at Filters
     * @return
     */
    public FiltersPO clickClearButton() {
        waitForElementIsClickable(clearButton);
        click(clearButton);
        return this;
    }

    /***
     * Clicks Done Button at Filters
     * @return
     */
    public FiltersPO clickDoneButton() {
        waitForElementIsClickable(doneButton);
        click(doneButton);
        return this;
    }

    /**
     * Slides elements
     * Slide from left -> right give positive offsets
     * Slide from right <- left give negative offsets
     *  @param slider
     * @param xOffset
     * @param yOffset
     * @return
     */
    public FiltersPO slideFilters(SlidersEnum slider, int xOffset, int yOffset) {
        switch (slider) {
            case minPrice:
                waitForElementIsClickable(priceSliderButtonMin);
                slideElement(priceSliderButtonMin, xOffset, yOffset);
                break;
            case maxPrice:
                waitForElementIsClickable(priceSliderButtonMax);
                slideElement(priceSliderButtonMax, xOffset, yOffset);
                break;
            case travelTime:
                waitForElementIsClickable(travelTimeSliderButton);
                slideElement(travelTimeSliderButton, xOffset, yOffset);
                break;
            case goDepartureMax:
                waitForElementIsClickable(goSliderButtonMax);
                slideElement(goSliderButtonMax, xOffset, yOffset);
                break;
            case returnArrivalMin:
                waitForElementIsClickable(returnSliderButtonMin);
                slideElement(returnSliderButtonMin, xOffset, yOffset);
                break;
            default:
        }
        return this;

    }

}