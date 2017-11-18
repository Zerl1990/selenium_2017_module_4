package com.qalabs.gmail;

import com.qalabs.common.BasePage;
import com.qalabs.utils.PropertyReader;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lmrivas on 14/11/2017.
 */
public class GmailLoginPage extends BasePage {
    // Page is dynamic, control view state with this internal toggle
    private boolean emailView = true;

    @FindBy(how = How.ID, using = "identifierId")
    private WebElement emailTextBox;

    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    private WebElement passwordTextBox;

    @FindBy(how = How.XPATH, using = "//div[@role='button' and contains(@id, \"Next\")]")
    private WebElement nextButton;

    @FindBy(how = How.ID, using = "//div[@aria-live='assertive']/div")
    private List<WebElement> errorMessages;

    @FindBy(how = How.ID, using = "logo")
    private WebElement logoIcon;

    @FindBy(how = How.ID, using = "initialView")
    private WebElement presentationDiv;

    public GmailLoginPage(WebDriver driver) {
        super(driver, PropertyReader.getProperty("endpoints.properties", "GMAIL_LOGIN_URL"));
    }

    @Override
    public void open() {
        this.driver.get(this.baseUrl);
        this.emailView = true;
    }

    public GmailMainPage login (String email, String password) {
        this.emailTextBox.clear();
        this.emailTextBox.sendKeys(email);
        this.nextButton.click();
        this.emailView = false;
        if (this.checkErrorMsg() || !this.isLoaded()) {
            return null;
        }
        this.passwordTextBox.clear();
        this.passwordTextBox.sendKeys(password);
        this.nextButton.click();
        return this.checkErrorMsg() ? null : new GmailMainPage(driver);
    }

    private boolean checkErrorMsg() {
        if (this.errorMessages.size() > 0) {
            this.logger.info("Got errors while logging");
            return true;
        } else {
            this.logger.info("Didn't get errors while logging");
            return false;
        }
    }

    @Override
    public boolean isLoaded() {
        try {
            int timeout = Integer.parseInt(PropertyReader.getProperty("timeouts.properties", "GMAIL_LOGIN_PAGE"));
            WebDriverWait wait = new WebDriverWait(this.driver, timeout);
            WebElement toWait = emailView ?  this.emailTextBox : this.passwordTextBox;
            wait.until(ExpectedConditions.elementToBeClickable(toWait));
            wait.until(ExpectedConditions.visibilityOf(toWait));
            wait.until(ExpectedConditions.attributeToBe(toWait, "tabindex","0"));
            return true;
        } catch (TimeoutException exception) {
            this.logger.error("Gmail main page not loaded: " + exception);
            return false;
        }
    }
}
