package com.testingmint.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBePresent(WebElement element) {
        wait.until(driver -> {
            try {
                org.openqa.selenium.Rectangle rect = element.getRect();
                return rect.getHeight() > 0 && rect.getWidth() > 0;
            } catch (Exception e) {
                return false;
            }
        });
    }

    protected void executeJavaScript(String script, Object... args) {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(script, args);
    }

    protected void click(WebElement element) {
        acceptAlertIfPresent();
        waitForElementToBeVisible(element);
        element.click();
    }

    protected void type(WebElement element, String text) {
        acceptAlertIfPresent();
        waitForElementToBeVisible(element);
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        
        // Type character by character to be more human-like and trigger app state
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            try { Thread.sleep(50); } catch (InterruptedException e) {}
        }
        
        element.sendKeys(Keys.TAB);
        executeJavaScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true })); arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", element);
    }

    protected void selectDropdown(WebElement element, String visibleText) {
        acceptAlertIfPresent();
        waitForElementToBeVisible(element);
        // Do not click the select element directly as it can cause deselection in some reactive apps
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
        executeJavaScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", element);
        try { Thread.sleep(500); } catch (InterruptedException e) {}
    }

    public void acceptAlertIfPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Handling Unexpected Alert: " + alert.getText());
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No alert to handle
        }
    }
}
