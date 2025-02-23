package io.paysky.qc.pages.Create_Order;

import io.paysky.qc.GlobalProperties;
import io.paysky.qc.utilities.selenium.DriverFactory;
import io.paysky.qc.utilities.testdata.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class CreateOrder {

    private WebDriver driver;

    public CreateOrder() {
        driver = DriverFactory.getDriver();
    }

    public void Create_Order() throws InterruptedException {
        driver.findElement(By.xpath("//input[@type='text']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Constant.ProductName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@id='zoom-container']/div/app-main-landing/div/app-category-products/section/div/div/div/div[2]/div/a/app-mtn-product-card/div/div[4]/button[2]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@id='header-zoom-container']/div[3]/div[3]/a/span")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='zoom-container']/div/app-main-landing/div/app-index/section/div/div/div[2]/app-checkout-card/div/section/button")).click();
        Thread.sleep(15000);
        driver.findElement(By.xpath("//div[@id='zoom-container']/div/app-main-landing/div/app-index/div[2]/section/div[2]/app-payment-cart/div/section/button")).click();
        Thread.sleep(30000);
        driver.switchTo().frame(0);
        driver.findElement(By.id("payByWallet")).click();
        Thread.sleep(50000);
        driver.get(GlobalProperties.getProperty("Customer_Portal_Orders"));
        Thread.sleep(10000);
        Constant.OrderNumber  = driver.findElement(By.xpath("/html/body/app-root/section/div/div/div/app-main-landing/div/app-index/div/section/div/div[3]/a[1]/div[1]/div[2]/span[2]")).getText();
       // Constant.OrderNumber = orderNumber;
//        String actualText = driver.findElement(By.xpath("/html/body/app-root/section/div/div/div/app-main-landing/div/app-order-placed/section/div/div/div/div/div[2]/p[1]")).getText();
//        Assert.assertEquals("Text does not match!", " Your order has been placed successfully ", actualText);
//        Thread.sleep(5000);
    }
}