package tests;

import base.BaseTest;
import dtos.Travel;
import enums.SlidersEnum;
import org.testng.annotations.Test;

/***
 * Tests Verifies results
 * - Case 1 : No results exist for the selected filters
 * */

public class Test_A2_FilterinNoResults extends BaseTest {

    @Test(alwaysRun = true, description = "Search for a Flight from Athens to Berlin. Update Filters(Stops, Price, Travel Time). Verify no results are available.")
    public void testStep_1() {
        searchFlightAndApplyFilters();
        results.verifyNoFlights();
    }



    /**
     * Search for Flight Ath - Berlin
     * with return
     * for one person
     * */
    private void searchFlightAndApplyFilters() {
        Travel travel2 = new Travel();
        travel2.toCity = "Berlin";

        homepage.searchForFlight(travel2);
        filters.clickFiltersButton()
                .clickMaxOneStopButton()
                .slideFilters(SlidersEnum.maxPrice,-400,0) // set max price 206
                .slideFilters(SlidersEnum.travelTime,-600,0) // set max travel time 2h 30m
                .clickArrivalGoRadioButton()
                .clickDoneButton();

    }



}
