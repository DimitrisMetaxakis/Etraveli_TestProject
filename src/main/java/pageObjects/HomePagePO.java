package pageObjects;

import dtos.Travel;
import org.openqa.selenium.By;
import utilities.BaseActions;

public class HomePagePO extends BaseActions {

    //Home Page Locators
    private By destinationFromField = By.cssSelector("input#searchForm-singleBound-origin-input");
    private By destinationToField = By.cssSelector("input#searchForm-singleBound-destination-input");
    private By departureDateField = By.cssSelector("input[aria-label='Departure']");
    private By returnDateField = By.cssSelector("input[aria-label='Return']");
    private By searchFlightButton = By.cssSelector("button[data-testid='searchForm-searchFlights-button']");
    private By searchFlightBanner = By.cssSelector("div[data-testid='searchPage-searchForm']");
    private By acceptTermsButton = By.cssSelector("button[data-testid='cookieBanner-confirmButton']");
    private By destinationDropdown = By.cssSelector("div[data-testid='etiDropdownOption']");
    private By calendar = By.cssSelector("div.DayPicker");
    private By chooseMonthArrow = By.cssSelector("button[data-testid='searchForm-nextMonth-input']");
    private By calendarMonthText = By.cssSelector("div.DayPicker-Caption");

    /** locator with dynamic values */
    private By flightDates(String date) {
        String locatorText = "div.DayPicker-Day[aria-label='" + date + "']";
        return By.cssSelector(locatorText);
    }

    /**
     * Clicks Accept Terms Button
     * @return
     */
    public HomePagePO clickAcceptTermsButton(){
        waitForElementVisible(acceptTermsButton);
        click(acceptTermsButton);
        return this;
    }

    /**
     * Type country of Departure
     * @return
     */
    public HomePagePO fromInput(String country) {
        waitForElementVisible(destinationFromField);
        sendKeys(destinationFromField, country);
        waitForElementVisible(destinationDropdown);
        clickEnter(destinationFromField);
        return this;
    }

    /**
     * Type country of Arrival
     * @return
     */
    public HomePagePO toInput(String country) {
        waitForElementVisible(destinationToField);
        sendKeys(destinationToField, country);
        waitForElementVisible(destinationDropdown);
        clickEnter(destinationToField);
        return this;
    }

    /**
     * Type Departure Date
     * @return
     */
    public HomePagePO selectDepartureDate(String date, String month) {
        waitForElementVisible(departureDateField);
        click(departureDateField);
        waitForElementVisible(calendar);
        goToMonthCalendar(month);
        click(flightDates(date));
        return this;
    }

    /**
     * Click arrow to select month on calendar
     * @return
     */
    public HomePagePO goToMonthCalendar(String month){
        while (!getText(calendarMonthText).equalsIgnoreCase(month)) {
           click(chooseMonthArrow);
        }
        return this;
    }

    /**
     * Type Return Date
     * @return
     */
    public HomePagePO inputReturnDate(String date, String month) {
        waitForElementVisible(returnDateField);
        click(returnDateField);
        waitForElementVisible(calendar);
        goToMonthCalendar(month);
        click(flightDates(date));
        return this;
    }


    /**
     * Clicks Search Flight Button
     * @return
     */
    public HomePagePO clickSearchFlightButton() {
        waitForElementIsClickable(searchFlightButton);
        click(searchFlightButton);
        return this;
    }

    /**
     * Search Flight
     * @return
     */
    public void searchForFlight(Travel travel) {
        clickAcceptTermsButton().waitForElementVisible(searchFlightBanner);
        fromInput(travel.fromCity)
                .toInput(travel.toCity)
                .selectDepartureDate(travel.fromDate, travel.month)
                .inputReturnDate(travel.toDate, travel.month)
                .clickSearchFlightButton();
    }


}
