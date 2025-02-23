package io.paysky.qc;

import io.paysky.qc.pages.Create_Email_Notification.CreateEmailNotification;
import io.paysky.qc.pages.Login.LoginPage;
import io.paysky.qc.pages.Logout.Logout;
import io.paysky.qc.pages.OnboardingPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Subject_validation {

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
    @Description("Admin can create a new notification email with a valid subject")
    @Owner("Kareem Mohamed")
    public void testValidSubject() throws InterruptedException {
        loginPage.Login_admin_user();
        createEmailNotification.createAndSaveEmailNotification("Valid Subject","","test");

        logoutPage.Log_out_Admin_portal();
    }

    @Test(priority = 2, groups = {"End-To-End"})
    @Description("Admin cannot create a notification email with an invalid short subject")
    @Owner("Kareem Mohamed")
    public void testInvalidShortSubject() throws InterruptedException {
        loginPage.Login_admin_user();
        createEmailNotification.createAndSaveEmailNotification("H","","test");
        logoutPage.Log_out_Admin_portal();
    }

    @Test(priority = 3, groups = {"End-To-End"})
    @Description("Admin cannot create a notification email with an invalid long subject")
    @Owner("Kareem Mohamed")
    public void testInvalidLongSubject() throws InterruptedException {
        loginPage.Login_admin_user();
        createEmailNotification.createAndSaveEmailNotification("A".repeat(50),"","test"); // Use the long subject here
        logoutPage.Log_out_Admin_portal();
    }
    @Test(priority = 4, groups = {"End-To-End"})
    @Description("Admin cannot create a notification email without subject")
    @Owner("Kareem Mohamed")
    public void testEmptySubject() throws InterruptedException {
        loginPage.Login_admin_user();
        createEmailNotification.createAndSaveEmailNotification("","","test");
        logoutPage.Log_out_Admin_portal();
    }
}
