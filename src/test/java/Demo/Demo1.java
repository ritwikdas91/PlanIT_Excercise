package Demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chromium.ChromiumDriver;

public class Demo1 {

    public static void main(String[] args){

        WebDriverManager.chromedriver().setup();
//        System.setProperty("webdriver.chrome.driver","D:\\Project\\src\\main\\resources\\chromedriver.exe");
        WebDriver d = new ChromeDriver();
        d.manage().window().maximize();
        d.get("https://www.google.com.au/");
        d.findElement(By.name("q")).sendKeys("Ritwik");
        d.findElement(By.name("q")).submit();
        d.quit();

    }

}
