package com.qalabs.pageobjects;

import com.qalabs.gmail.GmailLoginPage;
import com.qalabs.gmail.GmailMainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GmailLoginTest extends BaseTest{
    @Test(description = "Login with correct credentials")
    public void loginPositiveTest() {
        GmailLoginPage loginPage = new GmailLoginPage(this.driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isLoaded(), "Gmail login page not loaded");
        GmailMainPage mainPage = loginPage.login(this.email, this.password);
        Assert.assertNotNull(mainPage, "Could'not login to gmail");
    }
}
