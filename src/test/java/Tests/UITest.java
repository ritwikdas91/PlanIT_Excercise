package Tests;


import DriverHandler.UserActions;
import POM.LoginPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import reporting.ExtentReports.ExtentTestManager;

public class UITest extends UserActions {

    @Test
    void UI_Test1(){

        System.out.println("test 1");
        LoginPage lg = new LoginPage();
        ExtentTestManager.startTest("UI_TC1","Stupid UI Tests");
        lg.navigatetoContactPage();


    }


}
