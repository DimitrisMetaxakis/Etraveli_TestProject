package tests;

import base.BaseTest;
import dtos.Travel;
import enums.SlidersEnum;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


/***
 * Tests Verifies results
 * - Case 1 : All Filters are selected in the default state
 * - Case 2 : Update Filters(Number of Stops, Price, Airlines, Departure Time, Arrival Time, Travel Time)
 * */

public class Test_A1_MultipleFiltering extends BaseTest {


    @Test(alwaysRun = true, description = "Search for a Flight from Athens to Thessaloniki. Verify summary results with all filters applied in default state.")
    public void testStep_1() {
        searchFlight();
        verifySummaryResultsStep1();
    }

    @Test(alwaysRun = true, description = "Search for a Flight from Athens to Thessaloniki. Verify number of flights with all filters applied in default state.", dependsOnMethods = "testStep_1")
    public void testStep_2() {
        results.verifyNumberOfFlights("745 Flights");
    }

    @Test(alwaysRun = true, description = "Update Filters(Number of Stops,Price,Airlines,Departure Time,Arrival Time,Travel Time),Verify results include only selected Flight Company", dependsOnMethods = "testStep_2")
    public void testStep_3() {
        filterFlightTestStep3();
        results.verifyCompanyName("Aegean Airlines");
    }

    @Test(alwaysRun = true, description = "Update Filters(Number of Stops,Price,Airlines,Departure Time,Arrival Time,Travel Time),Verify results include only selected range of Departure Times", dependsOnMethods = "testStep_3")
    public void testStep_4() {
        // outgoing flight ATH - THES : max 08:00 departure time -> verify departure time < 9
        // ingoing flight THES - ATH : min 18:00 arrival time -> verify departure time > 16
        results.verifyDepartureTime(9, 16);
    }

    @Test(alwaysRun = true, description = "Update Filters(Number of Stops,Price,Airlines,Departure Time,Arrival Time,Travel Time),Verify results include only selected range of Arrival Times", dependsOnMethods = "testStep_4")
    public void testStep_5() {
        // outgoing flight ATH - THES : max 08:00 departure time -> verify arrival time < 10
        // ingoing flight THES - ATH : min 18:00 arrival time -> verify arrival time > 17
        results.verifyArrivalTime(10, 17);
    }

    @Test(alwaysRun = true, description = "Update Filters(Number of Stops,Price,Airlines,Departure Time,Arrival Time,Travel Time),Verify results include only selected range of Flight Duration", dependsOnMethods = "testStep_5")
    public void testStep_6() {
        // max flight duration 8h
        results.verifyDurationTime(481, true);
    }


    /**
     * Search for Flight Ath - Thes
     * with return
     * for one person
     * */
    private void searchFlight() {
        Travel travel1 = new Travel();
        homepage.searchForFlight(travel1);
    }

    /**
     * Verify the Summary Results Table
     */
    public void verifySummaryResultsStep1() {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("Recommended", "CA$123.32");
        expectedMap.put("Promotion", "CA$123.32");
        expectedMap.put("Cheapest", "CA$123.32");
        expectedMap.put("Shortest", "CA$157.74");
        results.verifySummaryResults(expectedMap);
    }

    /**
     * Filter flight with :
     * - Non Stop
     * - Only Aegean Airline
     * - Max price = 350
     * - Max departure 08:00
     * - Min return 18:00
     * - Max travel time 8h
     */
    public void filterFlightTestStep3() {
        filters.clickFiltersButton()
                .clickNonStopFlight()
                .clickClearAllAirlinesButton()
                .clickAegeanAirlinesCheckbox()
                .slideFilters(SlidersEnum.maxPrice,-300,0) // set max price 350
                .slideFilters(SlidersEnum.goDepartureMax,-350,0) // set max departure 08:00
                .slideFilters(SlidersEnum.returnArrivalMin,400,0) // set min return 18:00
                .slideFilters(SlidersEnum.travelTime,-400,0) // set max travel time 8h
                .clickArrivalReturnRadioButton()
                .clickDoneButton();
    }

}
