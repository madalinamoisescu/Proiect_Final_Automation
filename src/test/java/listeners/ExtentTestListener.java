package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import utils.DriverFactory;

import java.lang.reflect.Field;
import java.nio.file.Path;

public class ExtentTestListener extends TestListenerAdapter {
    private final ExtentReports extent = utils.ExtentManager.getInstance();
    private final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest extentTest = test.get();
        addTestLogs(result, extentTest);
        extentTest.skip(result.getThrowable());

        test.get().pass("Test PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest extentTest = test.get();
        addTestLogs(result, extentTest);
        extentTest.fail(result.getThrowable());

        org.openqa.selenium.WebDriver driver = findDriver(result);
        if (driver != null) {
            Path screenshotPath = utils.ScreenshotUtils.takeScreenshot(driver, result.getName());
            if (screenshotPath != null) {
                String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                extentTest.addScreenCaptureFromBase64String(screenshot, result.getName());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest extentTest = test.get();
        addTestLogs(result, extentTest);
        extentTest.skip(result.getThrowable());

        test.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private org.openqa.selenium.WebDriver findDriver(ITestResult result) {
        org.openqa.selenium.WebDriver driver = DriverFactory.getExistingDriver();
        if (driver != null) {
            return driver;
        }

        Object testInstance = result.getInstance();
        if (testInstance == null) {
            return null;
        }

        Class<?> currentClass = testInstance.getClass();
        while (currentClass != null) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (org.openqa.selenium.WebDriver.class.isAssignableFrom(field.getType())) {
                    try {
                        field.setAccessible(true);
                        return (org.openqa.selenium.WebDriver) field.get(testInstance);
                    } catch (IllegalAccessException e) {
                        return null;
                    }
                }
            }
            currentClass = currentClass.getSuperclass();
        }

        return null;
    }

    private void addTestLogs(ITestResult result, ExtentTest extentTest) {
        for (String log : Reporter.getOutput(result)) {
            extentTest.log(Status.INFO, log);
        }
    }
}
