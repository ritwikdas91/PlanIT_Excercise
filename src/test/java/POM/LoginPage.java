package POM;

import DriverHandler.UserActions;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

@Slf4j
public class LoginPage extends UserActions {

    @FindBy(xpath = "//*[@id=\"login-form\"]/nav/button[1]")
    private WebElement personalBanking;
    @FindBy(xpath="//*[@id=\"entrypoint\"]/div/aside/nav/ul[2]/li[2]/a/span")
    private WebElement LogOut;

    @FindBy(xpath="/html/body/div[2]/div/div/div[1]/p[2]/a")
    private WebElement startShopping;
    @FindBy(xpath = "//*[@id=\"product-8\"]/div/p/a")
    private WebElement productList;

    @FindBy(id = "nav-contact")
    private WebElement jupiterContactPage;
    @FindBy(xpath="/html/body/div[2]/div/form/div/a")
    private WebElement submitButton;

    public LoginPage(){

        super();
        PageFactory.initElements(driverThread,this);

    }

    @Step("Login and do stuff")
    public void Login(){

        try {

            navigate("http://jupiter.cloud.planittesting.com");
            click(personalBanking);
            waitForElement(LogOut);

        }catch (Exception e){

            log.error(e.getMessage());

        }


    }

    @Step("Navigate to Contact Page")
    public void navigatetoContactPage(){

        try {

            navigate("http://jupiter.cloud.planittesting.com");
            waitForElement(startShopping);
            click(jupiterContactPage);
            waitForElement(submitButton);

        }catch (Exception e){

            log.error(e.getMessage());
            Assert.fail();
        }


    }

    @Step("Navigate to Shopping Page")
    public void navigatetoShoppingPage(){

        try {

            navigate("http://jupiter.cloud.planittesting.com");
            waitForElement(startShopping);
            click(startShopping);
            waitForElement(productList);

        }catch (Exception e){

            log.error(e.getMessage());
            Assert.fail();
        }


    }


}
