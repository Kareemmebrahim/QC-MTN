package io.paysky.qc.pages.Create_New_Product;

import io.paysky.qc.utilities.selenium.DriverFactory;
import io.paysky.qc.utilities.sharedComponent.SharedComponent;
import io.paysky.qc.utilities.testdata.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.nio.file.Paths;

public class CreateNewProduct {

    private WebDriver driver;

    public CreateNewProduct() {
        driver = DriverFactory.getDriver();
    }

    public void createNewProduct() throws InterruptedException {
        //catalog page
        driver.findElement(By.xpath("//a[contains(@href, '/MA/products/catalog')]")).click();
        Thread.sleep(8000);
        // Add product button
        driver.findElement(By.xpath("//button[contains(.,' Add Product')]")).click();
        Thread.sleep(8000);
        // click on category and select paysky
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
        driver.findElement(By.xpath("//span[contains(.,'PaySky')]")).click();
        Thread.sleep(3000);
        //inserting the product name by random string
        driver.findElement(By.xpath("(//input[@type=\'text\'])[2]")).click();
        String Product_name = SharedComponent.getRandomNumberString();
        driver.findElement(By.xpath("(//input[@type=\'text\'])[2]")).sendKeys(Product_name);
        Constant.ProductName = Product_name;
        //inserting the Merchant SKU by Random string
        driver.findElement(By.xpath("(//input[@type=\'text\'])[3]")).click();
        driver.findElement(By.xpath("(//input[@type=\'text\'])[3]")).sendKeys(SharedComponent.getRandomNumberString());
        // insert the Quantity by static value
        driver.findElement(By.xpath("//input[@type='number']")).click();
        driver.findElement(By.xpath("//input[@type='number']")).sendKeys("100");
        //insert the minimum in stock
        driver.findElement(By.xpath("(//input[@type='number'])[2]")).click();
        driver.findElement(By.xpath("(//input[@type='number'])[2]")).sendKeys("1");
        //inserting the weight by static value and select the type of weight
        driver.findElement(By.xpath("(//input[@type='text'])[4]")).click();
        driver.findElement(By.xpath("/html/body/app-root/app-add-product/app-sidebar/div/div[2]/div[2]/form/div[1]/div[1]/div[2]/div[5]/div/div[2]/ng-select/div/div/div[3]")).click();
        driver.findElement(By.xpath("//span[contains(.,'g')]")).click();
        driver.findElement(By.xpath("(//input[@type='number'])[3]")).click();
        driver.findElement(By.xpath("(//input[@type='number'])[3]")).sendKeys("100");
        // insert the price by static value
        driver.findElement(By.xpath("(//input[@type='number'])[7]")).click();
        driver.findElement(By.xpath("(//input[@type='number'])[7]")).sendKeys("30000");
        //insert the description
        driver.findElement(By.id("floatingTextarea")).click();
        driver.findElement(By.id("floatingTextarea")).sendKeys("Description");
        //Add image
        String absolutePath = Paths.get("src/main/resources/image.jpeg").toAbsolutePath().toString();
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(absolutePath);
        Thread.sleep(8000);
        //click on next button
        driver.findElement(By.xpath("//button[contains(.,'Next')]")).click();
        Thread.sleep(3000);
        //switch off the publish to storefront
        driver.findElement(By.cssSelector("#mat-mdc-slide-toggle-1-button .mdc-switch__icon--off")).click();
        Thread.sleep(3000);
        //click on confirm button
        driver.findElement(By.xpath("//button[contains(.,'Confirm')]")).click();
        Thread.sleep(3000);
        //click on ok button
        driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
        Thread.sleep(5000);
    }
}
