package com.testingmint.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(css = "[data-testid='flight-count']")
    private WebElement flightCount;

    @FindBy(xpath = "//button[contains(@data-testid, 'btn-view-seats')]")
    private List<WebElement> selectSeatsButtons;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public String getFlightCountText() {
        waitForElementToBeVisible(flightCount);
        return flightCount.getText();
    }

    public void selectFirstFlight() {
        if (!selectSeatsButtons.isEmpty()) {
            click(selectSeatsButtons.get(0));
        } else {
            throw new RuntimeException("No flights found!");
        }
    }
}
