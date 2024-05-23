package selenium4features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class FirstClass {

    public static void main(String[] args) {
        WebDriver d = new ChromeDriver(); // This shows in Selenium 4 has an alternative WebDriver Manager of Boni Garcia which was used to instantiate the browser driver without configuring it using System.setProperty("webdriver.chrome.driver", "ChromeDriverPath\\chromedriver.exe");
        d.get("https://google.com");
        System.out.println(d.getTitle());
        WebElement googleSearchWE = d.findElement(By.xpath("(//input[@value='Google Search'])[last()]"));
        d.findElement(RelativeLocator.with(By.tagName("input")).toRightOf(googleSearchWE)).click();//Here I have used the google search button and without passing the webelement of I am feeling lucky button I have used the Relativelocator class and to the right of method
        System.out.println(d.getTitle());
        d.quit();
    }
}
