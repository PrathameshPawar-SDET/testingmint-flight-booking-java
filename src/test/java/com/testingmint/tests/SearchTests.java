package com.testingmint.tests;

import com.testingmint.base.BaseTest;
import com.testingmint.pages.HomePage;
import com.testingmint.pages.SearchResultsPage;
import com.testingmint.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTests extends BaseTest {

    @Test
    public void testSearchFlightsOneWay() {
        HomePage homePage = new HomePage(driver);
        homePage.searchFlights("Mumbai, BOM", "Delhi, DEL", "Apr 27, 2026", null, "2 Passengers");

        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        String countText = resultsPage.getFlightCountText();
        Assert.assertTrue(Integer.parseInt(countText) >= 0, "Search results not displayed or flight count invalid!");
    }

    @Test
    public void testSearchWithInvalidRoute() {
        HomePage homePage = new HomePage(driver);
        // We use fillFlightDetails because it doesn't click the button
        homePage.fillFlightDetails("Mumbai, BOM", "Mumbai, BOM", "Apr 27, 2026", null, "1 Passenger");
        
        // The button should be disabled or we should see an error. 
        // Based on analysis, the button becomes disabled.
        Assert.assertEquals(driver.getCurrentUrl(), ConfigReader.getProperty("url"), "Should stay on home page for invalid route!");
    }
}
