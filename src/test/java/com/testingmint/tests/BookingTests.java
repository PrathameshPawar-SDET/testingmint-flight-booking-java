package com.testingmint.tests;

import com.testingmint.base.BaseTest;
import com.testingmint.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingTests extends BaseTest {

    @Test
    public void testE2EFlightBooking() {
        HomePage homePage = new HomePage(driver);
        homePage.searchFlights("Mumbai, BOM", "Delhi, DEL", "2026-11-20", null, "1 Passenger");

        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        String flightCount = resultsPage.getFlightCountText();
        System.out.println("Flights found: " + flightCount);
        Assert.assertTrue(Integer.parseInt(flightCount) > 0, "No flights found!");

        resultsPage.selectFirstFlight();

        SeatSelectionPage seatPage = new SeatSelectionPage(driver);
        seatPage.selectSeat("6A");
        seatPage.clickProceed();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("testuser", "password");

        // After login, we might need to click proceed again or it might auto-redirect
        // Based on my manual exploration, I had to click proceed again.
        seatPage.clickProceed();

        PassengerDetailsPage detailsPage = new PassengerDetailsPage(driver);
        detailsPage.fillPassengerDetails("John Doe", "A1234567", "30", "Male");
        detailsPage.clickConfirmBooking();

        // Validation of success message or redirection to confirmation page
        // (Assuming there's a success message or specific URL)
        Assert.assertTrue(driver.getCurrentUrl().contains("confirmation") || driver.getPageSource().contains("Booking Confirmed"), 
            "Booking failed or confirmation page not shown!");
    }
}
