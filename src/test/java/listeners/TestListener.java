package listeners;

import io.qameta.allure.Allure;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.util.List;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        List<String> output = Reporter.getOutput(result);
        if (!output.isEmpty()) {
            StringBuilder logBuilder = new StringBuilder();
            for (String line : output) {
                logBuilder.append(line).append("\n");
            }
            Allure.addAttachment("TestNG Reporter Logs", "text/plain", logBuilder.toString());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        List<String> output = Reporter.getOutput(result);
        if (!output.isEmpty()) {
            StringBuilder logBuilder = new StringBuilder();
            for (String line : output) {
                logBuilder.append(line).append("\n");
            }
            Allure.addAttachment("TestNG Reporter Logs (FAILED)", "text/plain", logBuilder.toString());
        }
    }
}