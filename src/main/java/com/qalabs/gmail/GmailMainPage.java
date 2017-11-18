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
 * Created by lmrivas on 14/11/2017.
 */
public class GmailMainPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[@role='heading']/*[@role='tab']")
    List<WebElement> tabs;

    @FindBy(how = How.XPATH, using = "//*[@role='button' and contains(@title,'Refresh')]")
    WebElement refreshButton;

    @FindBy(how = How.XPATH, using = "//*[@role='button' and contains(@href, 'accounts')]")
    WebElement profileButton;

    @FindBy(how = How.XPATH, using = "//*[@role='button' and contains(text(), 'COMPOSE')]")
    WebElement composeButton;

    @FindBy(how = How.XPATH, using = "//*[contains(@aria-label, 'Account Info')]")
    WebElement accountDiv;

    public GmailMainPage(WebDriver driver) {
        super(driver, PropertyReader.getProperty("endpoints.properties", "GMAIL_MAIN_URL"));
    }

    public void clickRefresh() {
        this.logger.info("Clicking refresh button");
        this.refreshButton.click();
    }

    public void clickProfileButton() {
        this.logger.info("Clicking profile button");
        this.profileButton.click();
    }

    public GmailComposePage clickCompose() {
        this.logger.info("Clicking compose button");
        this.composeButton.click();
        return new GmailComposePage(this.driver);
    }

    public void selectPrimaryTab() {
        this.logger.info("Selecting primary tab");
        this.tabs.get(0).click();
    }

    public void selectSocialTab() {
        this.logger.info("Selecting social tab");
        this.tabs.get(1).click();
    }

    public void selectPromotionsTab() {
        this.logger.info("Selecting promotions tab");
        this.tabs.get(2).click();
    }

    public String getSelectedTab() {
        for(WebElement tab: this.tabs) {
            if(tab.getAttribute("aria-selected").equals("true"))
                return tab.getText();
        }
        return "NA";
    }

    public Boolean isAccountProfileVisible() {
        return this.accountDiv.isEnabled();
    }

    @Override
    public boolean isLoaded() {
        try {
            int timeout = Integer.parseInt(PropertyReader.getProperty("timeouts.properties", "GMAIL_MAIN_PAGE"));
            WebDriverWait wait = new WebDriverWait(this.driver, timeout);
            wait.until(ExpectedConditions.visibilityOfAllElements(this.tabs));
            return true;
        } catch (TimeoutException exception) {
            this.logger.error("Gmail main page not loaded: " + exception);
            return false;
        }
    }
}
