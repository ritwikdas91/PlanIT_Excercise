package DriverHandler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;


public class Initializer {

    private static final Logger logger = LogManager.getLogger(Initializer.class);

    private static WebDriver driverThread = null;

    public Initializer(){

        System.out.println("in Constrctructor:Initializer");

    }

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) {
        drvInitializer(browser);
    }

    public WebDriver getDriver() {
        return driverThread;
    }

    private synchronized void drvInitializer(String browser) {

        try {

            switch (browser) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverThread = new FirefoxDriver();
                    logger.info("Initiating firefox driver");
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverThread = new ChromeDriver();
                    logger.info("Initiating chrome driver");
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driverThread = new EdgeDriver();
                    logger.info("Initiating edge driver");
                    break;
                default:
                    logger.info("Browser listed not supported");
            }
            System.out.println("log drive");

        } catch (Exception e) {
            logger.error(e);
        }
    }



    @AfterClass
    public void tearDown() {
        try {

        } catch (Exception e) {
            logger.info("Performance tests not included");
        } finally {
            driverThread.quit();
        }
    }


}
