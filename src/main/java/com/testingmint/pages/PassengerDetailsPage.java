package com.testingmint.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PassengerDetailsPage extends BasePage {

    @FindBy(css = "[data-testid='passenger-name-0']")
    private WebElement nameInput;

    @FindBy(css = "[data-testid='passenger-passport-0']")
    private WebElement passportInput;

    @FindBy(css = "[data-testid='passenger-age-0']")
    private WebElement ageInput;

    @FindBy(css = "[data-testid='passenger-gender-0']")
    private WebElement genderSelect;

    @FindBy(css = "[data-testid='btn-pay']")
    private WebElement confirmBookingBtn;

    @FindBy(css = "[data-testid='success-booking-id']")
    private WebElement successBookingId;

    public PassengerDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void fillPassengerDetails(String name, String passport, String age, String gender) {
        type(nameInput, name);
        type(passportInput, passport);
        type(ageInput, age);
        selectDropdown(genderSelect, gender);
    }

    public void clickConfirmBooking() {
        click(confirmBookingBtn);
        waitForElementToBeVisible(successBookingId);
    }
}
