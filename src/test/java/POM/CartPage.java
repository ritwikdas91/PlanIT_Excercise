package POM;

import DriverHandler.UserActions;
import io.qameta.allure.Step;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

@Slf4j
public class CartPage extends UserActions {

    @FindBy(xpath = "/html/body/div[2]/div/form/table/tfoot/tr[2]/td/ng-confirm/a")
    private WebElement emptyCart;

    @FindBy(xpath = "/html/body/div[2]/div/form/table/tbody/tr")
    private List<WebElement> resultTable;
    @FindBy(xpath = "/html/body/div[2]/div/form/table/tfoot/tr[1]")
    private WebElement totalValue;

    public CartPage(){

        super();
        PageFactory.initElements(driverThread,this);

    }



    @Step("Validate Cart Page Data")
    public void verifyCartData(String itemName, float price, float subTotal, float total ){

        try {

            for(int i=0;i<resultTable.size();i++){

                if(resultTable.get(i).findElements(By.tagName("td")).get(0).getText().equalsIgnoreCase(itemName)){

                    Assert.assertEquals(Float.parseFloat(resultTable.get(i).findElements(By.tagName("td")).get(1).getText().substring(1)), price);
                    Assert.assertEquals(Float.parseFloat(resultTable.get(i).findElements(By.tagName("td")).get(3).getText().substring(1)), subTotal);
                    break;
                }

            }
            waitForElement(totalValue);
            System.out.println(totalValue.getText().substring(7));
            Assert.assertEquals(Float.parseFloat(totalValue.getText().substring(7)), total);

        }catch (Exception e){

            log.error(e.getMessage());
            Assert.fail();

        }


    }


}
