package io.paysky.qc.pages.Create_Email_Notification;

import io.paysky.qc.utilities.selenium.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateEmailNotification {
    private final WebDriver driver;

    // Constructor to initialize WebDriver
    public CreateEmailNotification() {
        this.driver = DriverFactory.getDriver();
    }

    // Helper method to wait for an element to be visible
    private WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Helper method to fill the Subject field
    private void fillSubjectField(String subject) {
        WebElement subjectField = waitForElement(By.xpath("//textarea[@title='Subject']"));
        subjectField.clear();
        subjectField.sendKeys(subject);
    }

    // Helper method to fill the CC and BCC fields
    private void fillCcBccField(String Email) {
        //insert Emails in CC Field
        WebElement CcField = waitForElement(By.xpath("//textarea[@id='inputTemplate' and @title='CC']"));
        CcField.clear();
        CcField.sendKeys(Email);

        //inset Emails in Bcc Field
        WebElement BccField = waitForElement(By.xpath("//textarea[@id='inputTemplate' and @title='BCC']"));
        BccField.clear();
        BccField.sendKeys(Email);
    }

    //helper method to fill Description field with rich text
    private void fillDescriptionField(String RichText){
        WebElement descriptionField = waitForElement(By.xpath("/html/body/app-root/app-notifications-details/app-sidebar/div/div[2]/div/div[2]/form/div/div[1]/rich-text-editor/ckeditor/div[2]/div[2]/div"));
        descriptionField.sendKeys(RichText);
    }

    //Method to check if an error message is displayed with invalid emails
    public boolean ErrorMessageForInvalidEmail() {
       try {
           WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                   .until(ExpectedConditions
                           .visibilityOfElementLocated(By.xpath("/html/body/app-root/p-toast/div/p-toastitem/div/div/div/div[1]")));
           return errorMessage.isDisplayed();
       } catch (Exception e) {
           return false;
       }
    }

    public boolean IsOkButtonDisplayed() {
        try {
            WebElement okButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.//strong[text()='OK']]")));
            System.out.println("OK button is displayed, indicating success.");
            okButton.click();
            System.out.println("OK button is Clicked");
            return okButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Method to create and save an email notification
    public void createAndSaveEmailNotification(String subject,String Email,String RichText) throws InterruptedException {
        // Step 1: Navigate to the Notifications page
        driver.findElement(By.xpath("/html/body/app-root/app-fee-management/app-sidebar/div/div[1]/div/ul[8]/li/a/span")).click();
        Thread.sleep(10000);

        // Step 2: Click on "Add" to create a new notification
        driver.findElement(By.xpath("/html/body/app-root/app-notifications/app-sidebar/div/div[2]/div/div[1]/div[2]/button"))
                .click();
        Thread.sleep(2000);

        // Step 3: Select the "Email" channel
        driver.findElement(By.xpath("/html/body/app-root/app-notifications-details/app-sidebar/div/div[2]/div/div[2]/form/div/div[4]/app-select-dropdown/field-container/div/select"))
                .click();
        driver.findElement(By.xpath("/html/body/app-root/app-notifications-details/app-sidebar/div/div[2]/div/div[2]/form/div/div[4]/app-select-dropdown/field-container/div/select/option[2]"))
                .click();
        //select the Action
        driver.findElement(By.xpath("/html/body/app-root/app-notifications-details/app-sidebar/div/div[2]/div/div[2]/form/div/div[2]/app-select-dropdown/field-container/div/select"))
                .click();
        driver.findElement(By.xpath("/html/body/app-root/app-notifications-details/app-sidebar/div/div[2]/div/div[2]/form/div/div[2]/app-select-dropdown/field-container/div/select/option[1]"))
                .click();
        //select status
        driver.findElement(By.xpath("/html/body/app-root/app-notifications-details/app-sidebar/div/div[2]/div/div[2]/form/div/div[5]/app-select-dropdown/field-container/div/select"))
                .click();
        driver.findElement(By.xpath("/html/body/app-root/app-notifications-details/app-sidebar/div/div[2]/div/div[2]/form/div/div[5]/app-select-dropdown/field-container/div/select/option[1]"))
                .click();

        // Step 4: Fill the Subject field
        fillSubjectField(subject);

        //Step 5: Fill the CC and Bcc fields
        fillCcBccField(Email);

        //Step 6 : Fill the Description field
        fillDescriptionField(RichText);

        // Step 7: Save the notification
        WebElement addButton = waitForElement(By.xpath("//button[@class='add-button' and .//img[@alt='No Image']]"));
        addButton.click();
        Thread.sleep(10000);
    }
}
