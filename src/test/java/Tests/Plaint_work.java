package Tests;

import DriverHandler.UserActions;
import POM.CartPage;
import POM.ContactPage;
import POM.LoginPage;
import POM.shopPage;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.testng.annotations.Test;
import reporting.ExtentReports.ExtentTestManager;

public class Plaint_work extends UserActions {

    @Test
    void UI_Test1(){

        System.out.println("test 1");
        LoginPage lg = new LoginPage();
        ExtentTestManager.startTest("UI_TC1","PlanIT UI Tests1");
        lg.navigatetoContactPage();
        ContactPage cp = new ContactPage();
        cp.submitWithoutMandatory();


    }

    @Test(invocationCount = 5)
    void UI_Test2(){

        System.out.println("test 2");
        LoginPage lg = new LoginPage();
        ExtentTestManager.startTest("UI_TC2","PlanIT UI Tests2");
        lg.navigatetoContactPage();
        ContactPage cp = new ContactPage();
        cp.submitWithMandatory();


    }

    @Test
    void UI_Test3(){

        System.out.println("test 3");
        float[] priceArray = new float[3];
        float[] subTotalArray = new float[3];
        float[] returnArray = new float[2];
        LoginPage lg = new LoginPage();
        ExtentTestManager.startTest("UI_TC2","PlanIT UI Tests2");
        lg.navigatetoShoppingPage();
        shopPage sp = new shopPage();

        returnArray=sp.addItemstoCart("Stuffed Frog", 2);
        priceArray[0] =returnArray[0];
        subTotalArray[0] = returnArray[1];
        returnArray=sp.addItemstoCart("Fluffy Bunny", 2);
        priceArray[1] =returnArray[0];
        subTotalArray[1] = returnArray[1];
        returnArray=sp.addItemstoCart("Valentine Bear", 3);
        priceArray[2] =returnArray[0];
        subTotalArray[2] = returnArray[1];
        sp.navigateToCart(7);

        CartPage crp = new CartPage();
        crp.verifyCartData("Stuffed Frog", priceArray[0], subTotalArray[0], subTotalArray[0]+subTotalArray[1]+subTotalArray[2]);
        crp.verifyCartData("Fluffy Bunny", priceArray[1], subTotalArray[1], subTotalArray[0]+subTotalArray[1]+subTotalArray[2]);
        crp.verifyCartData("Valentine Bear", priceArray[2], subTotalArray[2], subTotalArray[0]+subTotalArray[1]+subTotalArray[2]);

    }

}
