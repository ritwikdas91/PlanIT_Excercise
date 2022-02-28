
package reporting.Listeners;

import DriverHandler.getDriverThread;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import logging.TestStatus;
import reporting.ExtentReports.ExtentManager;
import reporting.ExtentReports.ExtentTestManager;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class TestListener extends getDriverThread implements ITestListener {

    private TestStatus testStatus;
//    private ResultSender rs= new ResultSender();

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driverThread);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("I am in onFinish method " + iTestContext.getName());
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        this.testStatus = new TestStatus();
        log.info("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), "");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
//        this.sendStatus(iTestResult,"PASS");
        log.info("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            if (this.driverThread != null) {
                //        this.sendStatus(iTestResult,"FAIL");
                saveScreenshotPNG();
                log.error("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
                Object testClass = iTestResult.getInstance();
                this.driverThread = ((getDriverThread) testClass).getDriver();
                String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driverThread).
                        getScreenshotAs(OutputType.BASE64);
                ExtentTestManager.getTest().log(Status.FAIL, "Test Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
//        this.sendStatus(iTestResult,"SKIP");
        log.warn("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.error("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) this.driverThread).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "0", type = "text/plain")
    public static String saveTextLogs(String message) {
        return message;
    }
//    private void sendStatus(ITestResult iTestResult, String status){
//        this.testStatus.setTestClass(iTestResult.getTestClass().getName());
//        this.testStatus.setDescription(iTestResult.getMethod().getDescription());
//        this.testStatus.setStatus(status);
//        this.testStatus.setExecutionDate(LocalDateTime.now().toString());
//        rs.send(this.testStatus);
//    }
}
