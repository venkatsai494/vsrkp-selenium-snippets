package stackoverflow;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class FullPageScreenshot {

    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.amazon.in/");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            long pageHeight = (Long) js.executeScript("return document.body.scrollHeight");

            // Adjust window size to full page height
            driver.manage().window().setSize(new Dimension(1920, (int) pageHeight));

            // Take a screenshot of the full page
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("screenshot.png"));

            System.out.println("Screenshot captured successfully.");
        } catch (IOException e) {
            throw new RuntimeException("Error while saving screenshot", e);
        } finally {
            // Close the driver
            driver.quit();
        }
    }
}
