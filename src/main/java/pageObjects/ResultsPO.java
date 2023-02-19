package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.BaseActions;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultsPO extends BaseActions {

    private By resultsTable = By.cssSelector("div[data-testid='resultPage-searchResults']");

    private By resultsNumber = By.cssSelector("span[data-testid='resultPage-filters-text'] ~ span");
    private By summaryResults = By.cssSelector("button[data-testid='result-quick-sort-button'] span");

    private By flightResultRow = By.cssSelector("div[data-testid='tripDetails-segment']");
    private By companyName = By.cssSelector("div[data-testid='tripDetails-segment'] div img ~ div");
    private By originTime = By.cssSelector("div[data-testid='tripDetails-segment'] div[data-testid='trip-segment-origin-time']");
    private By travelDuration = By.cssSelector("div[data-testid='tripDetails-segment']  p[data-testid='searchResults-segment-duration']");
    private By destinationTime = By.cssSelector("div[data-testid='tripDetails-segment']  div[data-testid='trip-segment-destination-time']");


    /**
     * Returns a Map of summary data in the UI
     */
    public Map<String, String> getSummaryResults() {
        Map<String, String> data = new HashMap<String, String>();
        List<WebElement> elements = Collections.emptyList();
        elements = findElements(summaryResults);
        for (int i = 0; i < elements.size(); i += 2) {
            data.put(elements.get(i).getText().trim(), elements.get(i + 1).getText().trim());
        }
        return data;
    }

    /**
     * Verify summary results
     *
     * @return
     */
    public ResultsPO verifySummaryResults(Map<String, String> expectedMap) {
        Map<String, String> actualMap = getSummaryResults();
        actualMap.forEach((k, v) ->
                Assert.assertEquals(v.replaceAll("\\W", ""), expectedMap.get(k).replaceAll("\\W", ""), "Expected:" + expectedMap.get(k) + "VS Actual: " + v));
        return this;
    }

    /**
     * Verify number of flights
     *
     * @return
     */
    public ResultsPO verifyNumberOfFlights(String expectedFlights) {
        Assert.assertEquals(getText(resultsNumber).trim().replace(":", ""), expectedFlights);
        return this;
    }

    /**
     * Verify zero number of flights
     *
     * @return
     */
    public ResultsPO verifyNoFlights() {
        String[] values = getText(resultsNumber).trim().split(" ");
        int flightsNumber =  Integer.parseInt(values[2]);
        Assert.assertEquals(flightsNumber, 0);
        return this;
    }

    /**
     * Verify Company Name from Results rows
     */
    public ResultsPO verifyCompanyName(String name) {
        List<WebElement> rows = Collections.emptyList();
        rows = findElements(companyName);
        for (int i = 0; i < rows.size(); i++) {
            Assert.assertEquals(rows.get(i).getText().trim(), name, "Row: " + i + " Expected:" + name + "VS Actual: " + rows.get(i).getText());
        }
        return this;
    }

    /**
     * Verify Departure Time ,in Results rows, is withing the filter range
     * even rows : outbound flights departure time
     * odd rows : inbound flights departure time
     *
     * @param outgoingTime
     * @param incomingTime
     */
    public ResultsPO verifyDepartureTime(Integer outgoingTime, Integer incomingTime) {
        List<WebElement> rows = Collections.emptyList();
        rows = findElements(originTime);
        for (int i = 0; i < rows.size(); i++) {
            if (i % 2 == 0) {
                String[] values = rows.get(i).getText().split(":");
                int hour = Integer.parseInt(values[0]);
                Assert.assertTrue(hour < outgoingTime, "Row: " + i + " Expected max:" + outgoingTime + "VS Actual: " + hour);
            } else {
                String[] values = rows.get(i).getText().split(":");
                int hour = Integer.parseInt(values[0]);
                Assert.assertTrue(hour > incomingTime, "Row: " + i + " Expected min:" + incomingTime + "VS Actual: " + hour);
            }
        }
        return this;
    }

    /**
     * Verify Arrival Time ,in Results rows, is withing the filter range
     * even rows : outbound flights departure time
     * odd rows : inbound flights departure time
     *
     * @param outgoingTime
     * @param incomingTime
     */
    public ResultsPO verifyArrivalTime(Integer outgoingTime, Integer incomingTime) {
        List<WebElement> rows = Collections.emptyList();
        rows = findElements(destinationTime);
        for (int i = 0; i < rows.size(); i++) {
            if (i % 2 == 0) {
                String[] values = rows.get(i).getText().split(":");
                int hour = Integer.parseInt(values[0]);
                Assert.assertTrue(hour < outgoingTime, "Row: " + i + " Expected max:" + outgoingTime + "VS Actual: " + hour);
            } else {
                String[] values = rows.get(i).getText().split(":");
                int hour = Integer.parseInt(values[0]);
                Assert.assertTrue(hour > incomingTime, "Row: " + i + " Expected max:" + incomingTime + "VS Actual: " + hour);
            }
        }
        return this;
    }

    /**
     * Verify Duration Time ,in Results rows, is withing the filter range
     *
     * @param expectedDuration
     * @param shortFlight
     */
    public ResultsPO verifyDurationTime(Integer expectedDuration, Boolean shortFlight) {
        List<WebElement> rows = Collections.emptyList();
        rows = findElements(travelDuration);
        for (int i = 0; i < rows.size(); i++) {
            if (shortFlight) {
                String[] parts = rows.get(i).getText().replaceAll("\\W", "").split("min"); // Split the string into parts based on min string
                int minutes = Integer.parseInt(parts[0]); // Extract the minutes;
                Assert.assertTrue(minutes < expectedDuration, "Row: " + i + " Expected max:" + expectedDuration + "VS Actual: " + minutes);

            } else {
                String[] parts = rows.get(i).getText().replaceAll("\\W", "").split(" "); // Split the string into parts based on space
                int hours = Integer.parseInt(parts[0].replace("h", "")); // Extract the hours and remove the "h" character
                int minutes = Integer.parseInt(parts[1].replace("min", "")); // Extract the minutes and remove the "min" character
                Assert.assertTrue(hours < expectedDuration, "Row: " + i + " Expected max:" + expectedDuration + "VS Actual: " + hours);

            }
        }
        return this;
    }
}
