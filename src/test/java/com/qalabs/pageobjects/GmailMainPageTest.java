package com.qalabs.pageobjects;

import com.qalabs.gmail.GmailLoginPage;
import com.qalabs.gmail.GmailMainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GmailMainPageTest extends BaseTest{
    @Test(description = "Test main page attributes")
    public void loginPositiveTest() {
        GmailLoginPage loginPage = new GmailLoginPage(this.driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isLoaded(), "Gmail login page not loaded");
        GmailMainPage mainPage = loginPage.login(this.email, this.password);
        Assert.assertNotNull(mainPage, "Could'not login to gmail");
        Assert.assertTrue(mainPage.isLoaded(), "Gmail main page not loaded");
        mainPage.selectPrimaryTab();
        Assert.assertTrue(mainPage.getSelectedTab().toLowerCase().contains("primary"), "Primary tab not selected");
        mainPage.selectPromotionsTab();
        Assert.assertTrue(mainPage.getSelectedTab().toLowerCase().contains("promotions"), "Social tab not selected");
        mainPage.selectSocialTab();
        Assert.assertTrue(mainPage.getSelectedTab().toLowerCase().contains("social"), "Promotions tab not selected");
        mainPage.clickProfileButton();
        Assert.assertTrue(mainPage.isAccountProfileVisible(), "Account information not visible");
        mainPage.clickProfileButton();
        Assert.assertTrue(mainPage.isAccountProfileVisible(), "Account information still visible");
        mainPage.clickRefresh();
        Assert.assertTrue(mainPage.isLoaded(), "Gmail main page not re-loaded");
        mainPage.clickCompose();
    }
}
