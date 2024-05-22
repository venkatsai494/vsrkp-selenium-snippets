package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstClass {

    public static void main(String[] args) {
        WebDriver d = new ChromeDriver(); // This shows in Selenium 4 has an alternative WebDriver Manager of Boni Garcia which was used to instantiate the browser driver without configuring it using System.setProperty("webdriver.chrome.driver", "ChromeDriverPath\\chromedriver.exe");
        d.get("https://google.com");
        System.out.println(d.getTitle());
        d.quit();
    }
}
