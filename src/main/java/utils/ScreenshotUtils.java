package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtils {

    public static Path takeScreenshot(WebDriver driver, String fileName) {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(fileName, "image/png", new ByteArrayInputStream(screenshotBytes), ".png");
        } catch (Exception e) {
            System.out.println("Nu s-a putut atasa screenshot-ul in Allure: " + e.getMessage());
        }

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            Path screenshotsDirectory = Paths.get("screenshots");
            Files.createDirectories(screenshotsDirectory);
            Path screenshotPath = screenshotsDirectory.resolve(fileName + ".png");
            Files.copy(src.toPath(),
                    screenshotPath,
                    StandardCopyOption.REPLACE_EXISTING);
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
