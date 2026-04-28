package com.testingmint.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class HomePage extends BasePage {

    @FindBy(css = "input[value='oneway']")
    private WebElement oneWayRadio;

    @FindBy(css = "input[value='round']")
    private WebElement roundTripRadio;

    @FindBy(css = "[data-testid='source-input']")
    private WebElement sourceInput;

    @FindBy(css = "[data-testid='dest-input']")
    private WebElement destInput;

    @FindBy(css = "[data-testid='date-input']")
    private List<WebElement> dateInputs;

    @FindBy(css = "[data-testid='passengers-select']")
    private WebElement passengersSelect;

    @FindBy(css = "[data-testid='search-btn']")
    private WebElement searchBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void selectOneWay() {
        click(oneWayRadio);
    }

    public void selectRoundTrip() {
        click(roundTripRadio);
    }

    public void fillFlightDetails(String from, String to, String departureDate, String returnDate, String passengers) {
        boolean isRoundTrip = returnDate != null && !returnDate.trim().isEmpty();

        if (isRoundTrip) {
            selectRoundTrip();
        } else {
            selectOneWay();
        }
        
        // 1. Set passengers first
        selectDropdown(passengersSelect, passengers);
        
        // 2. Set locations
        type(sourceInput, from);
        type(destInput, to);
        
        // 3. Native date inputs require ISO values (yyyy-MM-dd), even when the UI displays "Apr 27, 2026".
        setDateInput(0, departureDate);
        
        if (isRoundTrip) {
            setDateInput(1, returnDate);
        }
    }
    
    private void setDateInput(int inputIndex, String dateStr) {
        String isoDate = normalizeDate(dateStr);
        WebElement dateInput = wait.until(driver -> dateInputs.size() > inputIndex ? dateInputs.get(inputIndex) : null);

        // Make the hidden native date input interactable enough for value checks.
        executeJavaScript(
            "var el = arguments[0];" +
            "el.style.opacity = '1';" +
            "el.style.display = 'block';" +
            "el.style.visibility = 'visible';",
            dateInput
        );
        waitForElementToBePresent(dateInput);
        
        // Use the native setter so React's controlled input state receives the update.
        executeJavaScript(
            "var el = arguments[0];" +
            "var value = arguments[1];" +
            "var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "setter.call(el, value);" +
            "el.dispatchEvent(new Event('input', { bubbles: true, cancelable: true }));" +
            "el.dispatchEvent(new Event('change', { bubbles: true, cancelable: true }));" +
            "el.dispatchEvent(new Event('blur', { bubbles: true }));",
            dateInput, isoDate
        );
        
        wait.until(driver -> isoDate.equals(dateInput.getAttribute("value")));
    }

    private String normalizeDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty");
        }

        String trimmedDate = dateStr.trim();
        List<DateTimeFormatter> supportedFormats = Arrays.asList(
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ofPattern("MMM d, uuuu", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("MMMM d, uuuu", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("d MMM uuuu", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("d MMMM uuuu", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("M/d/uuuu", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("MM/dd/uuuu", Locale.ENGLISH)
        );

        for (DateTimeFormatter formatter : supportedFormats) {
            try {
                return LocalDate.parse(trimmedDate, formatter).format(DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException ignored) {
                // Try the next accepted input format.
            }
        }

        throw new IllegalArgumentException(
            "Unsupported date format: " + dateStr + ". Use yyyy-MM-dd or a format like Apr 27, 2026."
        );
    }

    public void searchFlights(String from, String to, String departureDate, String returnDate, String passengers) {
        fillFlightDetails(from, to, departureDate, returnDate, passengers);
        click(searchBtn);
        acceptAlertIfPresent();
    }
}
