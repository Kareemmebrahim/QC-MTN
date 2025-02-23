package io.paysky.qc;

import io.paysky.qc.pages.*;
import io.paysky.qc.pages.Approve_Product.ApproveProduct;
import io.paysky.qc.pages.Create_New_Product.CreateNewProduct;
import io.paysky.qc.pages.Create_Order.CreateOrder;
import io.paysky.qc.pages.Dispatch_Order.DispatchOrder;
import io.paysky.qc.pages.Login.LoginPage;
import io.paysky.qc.pages.Logout.Logout;
import io.paysky.qc.utilities.ApiCaller;
import io.paysky.qc.utilities.Get_Request_Id;
import io.paysky.qc.utilities.testdata.Constant;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.AfterTest;

public class Create_order {

    final OnboardingPage onboardingPage = new OnboardingPage();
    final LoginPage loginPage = new LoginPage();
    final ApproveProduct  approveProduct= new ApproveProduct();
    final CreateNewProduct createNewProduct = new CreateNewProduct();
    final CreateOrder createOrder = new CreateOrder();
    final DispatchOrder dispatchOrder = new DispatchOrder();
    final Logout logoutPage = new Logout();

    @org.testng.annotations.Test(priority = 1)
    @Description("Check that the user can login with valid credentials")
    @Link("https://devops.paysky.io/DefaultCollection/Infinity/_workitems/edit/62811")
    @Owner("Mohamed Ehab")
    @Tag("End-To-End")

    public void LoginWithMerchant() throws Exception {
        onboardingPage.setUp();
        loginPage.Login_merchant_user();
    }

    @org.testng.annotations.Test(priority = 2)
    @Description("Create a new product")
    @Link("https://devops.paysky.io/DefaultCollection/Infinity/_workitems/edit/62811")
    @Owner("Mohamed Ehab")
    @Tag("End-To-End")
    public void CreateNewProduct() throws Exception {
        createNewProduct.createNewProduct();
        logoutPage.Log_out_merchant_portal();
    }

    @org.testng.annotations.Test(priority = 3)
    @Description("Login as operation user")
    @Link("https://devops.paysky.io/DefaultCollection/Infinity/_workitems/edit/62811")
    @Owner("Mohamed Ehab")
    @Tag("End-To-End")
    public void LoginWithOperation() throws Exception {
        onboardingPage.setUp();
        loginPage.Login_operation_user();
    }

    @org.testng.annotations.Test(priority = 4)
    @Description("Approve the product")
    @Link("https://devops.paysky.io/DefaultCollection/Infinity/_workitems/edit/62811")
    @Owner("Mohamed Ehab")
    @Tag("End-To-End")
    public void ApproveProduct() throws Exception {
        approveProduct.Approve_Product();
        logoutPage.Log_out_operation_portal();
    }

    @org.testng.annotations.Test(priority = 5)
    @Description("Login as customer user")
    @Link("https://devops.paysky.io/DefaultCollection/Infinity/_workitems/edit/62811")
    @Owner("Mohamed Ehab")
    @Tag("End-To-End")

    public void LoginWithCustomer() throws Exception {
        onboardingPage.setUp();
        loginPage.Login_customer_user();
    }

    @org.testng.annotations.Test(priority = 6)
    @Description("Create an order")
    @Link("https://devops.paysky.io/DefaultCollection/Infinity/_workitems/edit/62811")
    @Owner("Mohamed Ehab")
    @Tag("End-To-End")

    public void CreateOrder() throws Exception {
        createOrder.Create_Order();
    }

    @org.testng.annotations.Test(priority = 7)
    @Description("Dispatch the order")
    @Link("https://devops.paysky.io/DefaultCollection/Infinity/_workitems/edit/62811")
    @Owner("Mohamed Ehab")
    @Tag("End-To-End")

    public void DispatchOrder() throws Exception {
        onboardingPage.setUp();
        loginPage.Login_merchant_user();
        dispatchOrder.Dispatch_Order();
        logoutPage.Log_out_merchant_portal();
    }

    @org.testng.annotations.Test(priority = 8)
    @Description("Fetch the RequestId after order dispatch and delivered it")
    @Owner("kareem Mohamed")
    @Tag("End-To-End")
    public void DeliverOrder() {
        // Fetch the RequestId using the OrderNumber from Constant
        String orderNumber = Constant.OrderNumber; // Ensure this is set during CreateOrder
        String requestId = Get_Request_Id.getRequestId(orderNumber);

        if (requestId != null) {
            System.out.println("RequestId fetched after dispatch: " + requestId);

            // Send a POST request with the RequestId (using ApiCaller)
            ApiCaller.sendPostRequest(orderNumber);  // This will send the request using the API
        } else {
            System.err.println("Failed to fetch RequestId for OrderNumber: " + orderNumber);
        }
    }
    @AfterTest
    public void CloseDriver() throws InterruptedException {
        logoutPage.Close_driver();
    }
}