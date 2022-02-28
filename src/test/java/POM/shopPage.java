package POM;

import DriverHandler.UserActions;
import io.qameta.allure.Step;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

@Slf4j
public class shopPage extends UserActions {


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
    @FindBy(xpath = "/html/body/div[2]/div/ul/li")
    private List<WebElement> itemList;
    @FindBy(id = "nav-cart")
    private WebElement cartButtonText;
    @FindBy(xpath = "/html/body/div[2]/div/form/table/tfoot/tr[2]/td/ng-confirm/a")
    private WebElement emptyCart;


    public shopPage(){

        super();
        PageFactory.initElements(driverThread,this);

    }



    @Step("Add Products")
    public float[] addItemstoCart(String itemName, int itemCount){

        float price=0;
        float subTotal=0;
        float[] priceArray = new float[2];

        try {


            for(int i=0;i<itemList.size();i++){

                if(itemList.get(i).findElement(By.tagName("h4")).getText().equalsIgnoreCase(itemName)){


                    price=Float.valueOf(itemList.get(i).findElement(By.tagName("span")).getText().substring(1));
                    for (int j=0;j<itemCount;j++){

                        click(itemList.get(i).findElement(By.tagName("p")));
                        subTotal=subTotal+Float.valueOf(itemList.get(i).findElement(By.tagName("span")).getText().substring(1));

                    }
                    break;

                }

                System.out.println("Inside cart loop"+i);


            }
//            (Future Cart Count Validation enhancement)compareText(cartButtonText.getText(),String.format("Cart (%f)"),totalItem);
//            click(cartButtonText);
//            waitForElement(emptyCart);



        }catch (Exception e){

            log.error(e.getMessage());
            Assert.fail();

        }

        priceArray[0]=price;
        priceArray[1]=subTotal;
        return priceArray;
    }


    @Step("Verify Cart Item Numbers and Navigate to Cart Page")
    public void navigateToCart(int itemCount){

        try{

            if(cartButtonText.getText().contains(String.valueOf(itemCount))){

                click(cartButtonText);
                waitForElement(emptyCart);

            }

        }
        catch (Exception e){

            log.error(e.getMessage());
            Assert.fail();

        }


    }


}
