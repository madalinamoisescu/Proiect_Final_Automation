package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.nio.file.Path;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(Path.of("extent-reports", "ExtentReport.html").toString());
            reporter.config().setDocumentTitle("Reports Project Test Report");
            reporter.config().setReportName("Reports Project Automation Results");
            reporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Project", "Reports ");
            extent.setSystemInfo("Framework", "Selenium + TestNG");
        }

        return extent;
    }
}
