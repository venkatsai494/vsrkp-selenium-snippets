package selenium4features.cdp;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.emulation.Emulation;

import java.util.Optional;

public class GeoLocation {

    public static void main(String[] args){
        ChromiumDriver driver = new ChromeDriver(); //From selenium 4 ChromeDriver is no more implements from WebDriver but instead extends ChromiumDriver class which would allow us to use devTools, we are not using the driver object of type WebDriver here
        driver.manage().window().maximize();
        driver.get("https://my-location.org/");
        System.out.println("Current location is " + driver.findElement(By.xpath("//div[text()='Your Location']/following-sibling::div")).getText());
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(Optional.of(19.882722), Optional.of(75.320592), Optional.of(1)));
        driver.get("https://my-location.org/");//The problem with Google is it uses the ip address, bluetooth and wifi to get the current location of the device. Hence, using this website to get the location using coordinates
        System.out.println("The location as per provided longitude and latitude coordinates is " + driver.findElement(By.xpath("//div[text()='Your Location']/following-sibling::div")).getText());
    }
}
