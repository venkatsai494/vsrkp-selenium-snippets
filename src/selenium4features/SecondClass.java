package selenium4features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SecondClass {

    public static void main(String[] args) {

        //W3C Webdriver Standardization
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("w3c", true);
        //The W3C WebDriver standard defines a protocol for browser automation. This protocol is implemented by browser vendors, ensuring consistent behavior across different browsers.
        WebDriver d = new ChromeDriver(options);
        d.get("https://facebook.com");
        System.out.println(d.getTitle());
        d.quit();
    }
}
