package com.testingmint.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "[data-testid='login-username']")
    private WebElement usernameInput;

    @FindBy(css = "[data-testid='login-password']")
    private WebElement passwordInput;

    @FindBy(css = "[data-testid='login-submit']")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginBtn);
    }
}
