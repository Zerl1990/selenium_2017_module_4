package com.qalabs.pageobjects;

import com.qalabs.gmail.GmailComposePage;
import com.qalabs.gmail.GmailLoginPage;
import com.qalabs.gmail.GmailMainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GmailComposeTest extends BaseTest{
    @Test(description = "Test compose page attributes")
    public void loginPositiveTest() {
        GmailLoginPage loginPage = new GmailLoginPage(this.driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isLoaded(), "Gmail login page not loaded");
        GmailMainPage mainPage = loginPage.login(this.email, this.password);
        Assert.assertNotNull(mainPage, "Could'not login to gmail");
        Assert.assertTrue(mainPage.isLoaded(), "Gmail main page not loaded");
        GmailComposePage composePage = mainPage.clickCompose();
        Assert.assertTrue(composePage.isLoaded(), "Gmail compose page not loaded");
        Assert.assertTrue(composePage.isCCVisible(), "CC button not visible");
        Assert.assertTrue(composePage.isBCCVisible(), "BCC button not visible");
        composePage.setTo("juan.tester.123@gmail.com");
        composePage.setSubject("Hello world QA LABS!");
        composePage.writeEmailContent("Hello world QA LABS!");
        composePage.sendEmail();
        Assert.assertTrue(!composePage.isLoaded(), "Gmail compose page should not be loaded after sending email");
    }
}
