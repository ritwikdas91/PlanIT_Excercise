package DriverHandler;

import org.openqa.selenium.WebDriver;

public class getDriverThread extends Initializer{

    public WebDriver driverThread;

    public getDriverThread() {
        this.driverThread = super.getDriver();
    }

}
