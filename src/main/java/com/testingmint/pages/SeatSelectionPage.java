package com.testingmint.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SeatSelectionPage extends BasePage {

    @FindBy(css = "[data-testid='btn-proceed-booking']")
    private WebElement proceedBtn;

    public SeatSelectionPage(WebDriver driver) {
        super(driver);
    }

    public void selectSeat(String seatNumber) {
        WebElement seat = driver.findElement(By.cssSelector("[data-testid='seat-" + seatNumber + "']"));
        if (seat.getAttribute("class").contains("seat-booked")) {
            seat = driver.findElement(By.cssSelector("[data-testid^='seat-'].seat-available"));
        }
        click(seat);
        WebElement selectedSeat = seat;
        wait.until(driver -> selectedSeat.getAttribute("class").contains("seat-selected"));
    }

    public void clickProceed() {
        wait.until(driver -> proceedBtn.isEnabled());
        click(proceedBtn);
    }
}
