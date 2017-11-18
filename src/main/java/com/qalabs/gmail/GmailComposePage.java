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
import java.util.List;

/**
 * Created by lmrivas on 17/11/2017.
 */
public class GmailComposePage extends BasePage {

    @FindBy(how = How.XPATH, using = "//textarea[@name='to']")
    WebElement toTextBox;

    @FindBy(how = How.XPATH, using = "//input[@name='subjectbox']")
    WebElement subjectTextBox;

    @FindBy(how = How.XPATH, using = "//div[@role='textbox']")
    WebElement contentTextBox;

    @FindBy(how = How.XPATH, using = "//textarea[@name='cc']")
    WebElement ccTextBox;

    @FindBy(how = How.XPATH, using = "//textarea[@name='bcc']")
    WebElement bccTextBox;

    @FindBy(how = How.XPATH, using = "//span[@role='link' and contains(@aria-label, 'Add Cc')]")
    WebElement ccButton;

    @FindBy(how = How.XPATH, using = "//span[@role='link' and contains(@aria-label, 'Add Bcc')]")
    WebElement bccButton;

    @FindBy(how = How.XPATH, using = "//div[@role='button' and @aria-label='Send \u202A(Ctrl-Enter)\u202C']")
    WebElement sendButton;

    public GmailComposePage(WebDriver driver) {
        super(driver, PropertyReader.getProperty("endpoints.properties", "GMAIL_MAIN_URL"));
    }

    public boolean isCCVisible() {
        return this.ccButton.isDisplayed() && this.ccButton.isEnabled();
    }

    public boolean isBCCVisible() {
        return this.bccButton.isDisplayed() && this.bccButton.isEnabled();
    }

    public void setTo(String email) {
        this.logger.info("Set to: " + email);
        this.toTextBox.clear();
        this.toTextBox.sendKeys(email);
    }

    public void writeEmailContent(String content) {
        this.logger.info("Sending email content to: " + content);
        this.contentTextBox.clear();
        this.contentTextBox.sendKeys(content);
    }

    public void setSubject(String subject) {
        this.logger.info("Set subject to: " + subject);
        this.subjectTextBox.clear();
        this.subjectTextBox.sendKeys(subject);
    }

    public void sendEmail() {
        this.logger.info("Clicking send button");
        this.sendButton.click();
    }

    @Override
    public boolean isLoaded() {
        try {
            int timeout = Integer.parseInt(PropertyReader.getProperty("timeouts.properties", "COMPOSE_PAGE"));
            WebDriverWait wait = new WebDriverWait(this.driver, timeout);
            List<WebElement> toWait = new ArrayList<WebElement>();
            toWait.add(this.toTextBox);
            toWait.add(this.sendButton);
            toWait.add(this.contentTextBox);
            wait.until(ExpectedConditions.visibilityOfAllElements(toWait));
            return true;
        } catch (TimeoutException exception) {
            this.logger.error("Compose page not loaded: " + exception);
            return false;
        }
    }
}
