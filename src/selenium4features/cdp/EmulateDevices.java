package selenium4features.cdp;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.emulation.Emulation;
import org.openqa.selenium.devtools.v130.network.Network;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EmulateDevices {

    public static void main(String[] args) throws InterruptedException {
        ChromiumDriver driver = new ChromeDriver(); //From selenium 4 ChromeDriver is no more implements from WebDriver but instead extends ChromiumDriver class which would allow us to use devTools, we are not using the driver object of type WebDriver here
        driver.manage().window().maximize();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setDeviceMetricsOverride(820, 1180, 100, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
//      Point to be the noted teh 1180 and 820 are the dimensions of Ipad air which I checked in Chrome by selecting ipad air from toggle device bar desktop <-> Mobile when width is more than height this means the mobile/tablet is in landscape mode otherwise it is in portrait mode
//      The above method needs first 4 parameters to be mandatory hence we have provided first 4 paramters and rest 10 are optional and in case of optional we cannot keep it as null or blank we have to mention it as Optional.empty().
        driver.get("https://www.toolsqa.com/");
        Thread.sleep(3000);
        boolean preseveTheOrientation = false;
        try{
            driver.findElement(By.xpath("//a[text()='Selenium Training']")).click();
        }
        catch (ElementNotInteractableException e){
//          This shows that when we launch the mobile view in portrait mode then the Selenium Training link is going in the hamburger icon so we can do two things here, either change the view to landscape by making width more than height or click on hamburger icon and then click on Selenium training.
            if(preseveTheOrientation){
                driver.findElement(By.xpath("//button[@id='hamburger-menu']")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("(//a[text()='Selenium Training'])[2]")).click();
            }
            else{
                devTools.send(Emulation.setDeviceMetricsOverride(1180, 820, 100, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
                driver.findElement(By.xpath("//a[text()='Selenium Training']")).click();
            }
        }
        Assert.isTrue(driver.getTitle().equals("Tools QA - Selenium Training"), "Title is not as expected");
        Thread.sleep(3000);
//      We are using devTools.send() method to use the custom methods developed by Selenium team to perform operation on devTools CDP. But rarely there are some scenarios where we would not get the custom implementation of Selenium for few operations on devtools so for that we need to use driver.executeCdpCommand() method as below
        Map<String, Object> deviceParameters = new HashMap<>();
        deviceParameters.put("width", 375);//This parameters are for iPhone SE
        deviceParameters.put("height", 667);
        deviceParameters.put("deviceScaleFactor", 100);
        deviceParameters.put("mobile", true);
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceParameters);
        Thread.sleep(3000);
        driver.quit();
    }
}
