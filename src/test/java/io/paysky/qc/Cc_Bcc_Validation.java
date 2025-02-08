package io.paysky.qc;

import io.paysky.qc.pages.Create_Email_Notification.CreateEmailNotification;
import io.paysky.qc.pages.Login.LoginPage;
import io.paysky.qc.pages.Logout.Logout;
import io.paysky.qc.pages.OnboardingPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Cc_Bcc_Validation {
    private final OnboardingPage onboardingPage = new OnboardingPage();
    private final LoginPage loginPage = new LoginPage();
    private CreateEmailNotification createEmailNotification;
    final Logout logoutPage = new Logout();

    @BeforeClass
    public void setUp() {
        // Set up environment
        onboardingPage.setUp();
        createEmailNotification = new CreateEmailNotification();
    }

    @Test(priority = 1)
    @Description("Admin can create a new notification email with valid Email in CC and Bcc Fields")
    @Owner("Kareem Mohamed")
    public void testValidEmail() throws InterruptedException {
        loginPage.Login_admin_user();
        createEmailNotification.createAndSaveEmailNotification("Valid Subject", "kareem@test.com","test");
        Assert.assertTrue(createEmailNotification.SuccessMessageForValidEmail(),
                "the Creating Notification is failed");
        logoutPage.Log_out_Admin_portal();
    }

    @Test(priority = 2)
    @Description("Admin can create a new notification email with invalid Email in CC and Bcc Fields")
    @Owner("Kareem Mohamed")
    public void testInValidEmail() throws InterruptedException {
        loginPage.Login_admin_user();
        createEmailNotification.createAndSaveEmailNotification("Valid Subject", "kareem@test","test");
        Assert.assertTrue(createEmailNotification.ErrorMessageForInvalidEmail(),
                "Error message for invalid emails was not displayed!");
        logoutPage.Log_out_Admin_portal();
    }

    @Test(priority = 3)
    @Description("Admin can create a new notification email with valid Emails separated by Simi colon in CC and Bcc Fields")
    @Owner("Kareem Mohamed")
    public void testValidEmails() throws InterruptedException {
        loginPage.Login_admin_user();
        createEmailNotification.createAndSaveEmailNotification("Valid Subject", "kareem@test.com;Kareem2@test.com","test");
        Assert.assertTrue(createEmailNotification.ErrorMessageForInvalidEmail(),
                "Error message for invalid emails was not displayed!");
        logoutPage.Log_out_Admin_portal();
    }

    @Test(priority = 4)
    @Description("Admin can create a new notification email with invalid Emails separated by Simi colon in CC and Bcc Fields")
    @Owner("Kareem Mohamed")
    public void testInValidEmails() throws InterruptedException {
        loginPage.Login_admin_user();
        createEmailNotification.createAndSaveEmailNotification("Valid Subject", "kareem@testcom;Kareem2test.com","test");
        Assert.assertTrue(createEmailNotification.ErrorMessageForInvalidEmail(),
                "Error message for invalid emails was not displayed!");
        logoutPage.Log_out_Admin_portal();
    }

    @Test(priority = 5)
    @Description("Admin can create a new notification email with valid Emails separated by Comma in CC and Bcc Fields")
    @Owner("Kareem Mohamed")
    public void testInValidSeparation() throws InterruptedException {
        loginPage.Login_admin_user();
        createEmailNotification.createAndSaveEmailNotification("Valid Subject", "kareem@test.com;Kareem2@test.com","test");
        Assert.assertTrue(createEmailNotification.ErrorMessageForInvalidEmail(),
                "Error message for invalid emails was not displayed!");
        logoutPage.Log_out_Admin_portal();
    }
}
