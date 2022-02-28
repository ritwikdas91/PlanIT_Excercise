package POM;

import DriverHandler.UserActions;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

@Slf4j
public class ContactPage extends UserActions {

    @FindBy(xpath = "//*[@id=\"header-message\"]/div")
    private WebElement errorMessage;
    @FindBy(xpath="/html/body/div[2]/div/div")
    private WebElement successMessage;
    @FindBy(xpath="/html/body/div[2]/div/form/div/a")
    private WebElement submitButton;
    @FindBy(xpath="/html/body/div[2]/div/a")
    private WebElement backButton;
    @FindBy(id="forename")
    private WebElement foreName;
    @FindBy(id="email")
    private WebElement email;
    @FindBy(id="message")
    private WebElement messageBox;
    @FindBy(id="forename-err")
    private WebElement foreNameError;
    @FindBy(id="email-err")
    private WebElement emailError;
    @FindBy(id="message-err")
    private WebElement messageError;

    public ContactPage(){

        super();
        PageFactory.initElements(driverThread,this);

    }



    @Step("Catch Mandatory Fileds Error")
    public void submitWithoutMandatory(){

        try {

            click(submitButton);
            waitForElement(errorMessage);
            compareText(getText(errorMessage), "We welcome your feedback - but we won't get it unless you complete the form correctly.");
            compareText(getText(foreNameError), "Forename is required");
            compareText(getText(emailError), "Email is required");
            compareText(getText(messageError), "Message is required");


        }catch (Exception e){

            log.error(e.getMessage());
            Assert.fail();

        }


    }




    @Step("Catch Mandatory Fileds Error")
    public void submitWithMandatory(){

        try {

            enter(foreName, "PlanIT");
            enter(email, "email@fakedomain.com");
            enter(messageBox,"A Random Test Message");
            click(submitButton);
//            waitForElement(successMessage);
            waitForElement(backButton);
            System.out.println(getText(successMessage));
            compareText(getText(successMessage), "Thanks PlanIT, we appreciate your feedback.");



        }catch (Exception e){

            log.error(e.getMessage());
            Assert.fail();
        }


    }

}
