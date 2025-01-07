### **Desired Capabilities in Selenium**

**Desired Capabilities** is a class in Selenium used to define a set of key-value pairs that specify the configurations required by the WebDriver to initiate a browser session with specific settings. These configurations include browser name, browser version, operating system, and additional settings like enabling JavaScript, handling SSL certificates, or setting browser preferences.

### **Why Use Desired Capabilities?**

1.  **Browser Configuration:** Desired Capabilities allow you to define the type and version of the browser you want to run your tests on.

    ```java
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName("chrome");
    capabilities.setVersion("114.0");
    ```

2.  **Cross-Browser Testing:** It enables tests to run on different browsers by defining the specific browser and environment in which the test should execute.

3.  **Mobile Testing (Appium):** Desired Capabilities are essential for specifying the device and app configurations in mobile automation frameworks like Appium.

    ```java
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("deviceName", "Pixel 5");
    capabilities.setCapability("app", "/path/to/app.apk");
    ```

4.  **Handling SSL Certificates:** Configure browsers to accept insecure or self-signed SSL certificates.

    ```java
    capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    ```

5.  **Custom Browser Settings:** Modify browser behavior like setting custom proxy, enabling or disabling specific browser features, etc.

    ```java
    capabilities.setCapability("proxy", proxy);
    ```

6.  **Grid Configuration:** In Selenium Grid, Desired Capabilities help specify the target browser and platform on which the tests should run.

    ```java
    capabilities.setPlatform(Platform.WINDOWS);
    ```


* * *

### **Example: Using Desired Capabilities in Selenium with ChromeDriver**

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesExample {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability("acceptInsecureCerts", true);

        // Pass capabilities to ChromeDriver
        WebDriver driver = new ChromeDriver(capabilities);

        driver.get("https://example.com");
        System.out.println("Page Title: " + driver.getTitle());
        driver.quit();
    }
}
```

> **Note:** Starting from Selenium 4, `Options` classes (like `ChromeOptions`, `FirefoxOptions`) are preferred over `DesiredCapabilities` for setting browser-specific options.

### Migrating to Selenium 4 (Using ChromeOptions):

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeOptionsExample {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://example.com");
        System.out.println("Page Title: " + driver.getTitle());
        driver.quit();
    }
}
```
Yes, in **Selenium 4**, the use of `DesiredCapabilities` has been **deprecated** for setting browser-specific configurations. Instead, Selenium 4 promotes the use of **Options classes** for each browser to configure desired capabilities and settings.

### **Why was DesiredCapabilities Deprecated?**

1.  **Browser-Specific Configuration:** Each browser has its own set of capabilities and configurations. Using a unified class like `DesiredCapabilities` could lead to misconfigurations.

2.  **Improved Clarity and Type-Safety:** The `Options` classes are tailored to each browser (`ChromeOptions`, `FirefoxOptions`, etc.), making the code more readable and less prone to errors.

3.  **Better Maintenance:** Browser vendors now align their browser-specific options with the W3C WebDriver standard, ensuring compatibility and easier maintenance.


* * *

### **What Replaces DesiredCapabilities?**

Instead of using `DesiredCapabilities`, you now use **browser-specific options classes**, such as:

|      Browser      | Replacement for DesiredCapabilities |
|:-----------------:|:-----------------------------------:|
| Chrome            | ChromeOptions                       |
| Firefox           | FirefoxOptions                      |
| Edge              | EdgeOptions                         |
| Safari            | SafariOptions                       |
| Internet Explorer | InternetExplorerOptions             |

These options classes implement the `Capabilities` interface, which can be passed to the respective WebDriver.

* * *

### **Example of Replacing DesiredCapabilities in Selenium 4:**

#### **Selenium 3 (with DesiredCapabilities):**

```java
DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setCapability("acceptInsecureCerts", true);
WebDriver driver = new ChromeDriver(capabilities);
```

#### **Selenium 4 (with ChromeOptions):**

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeOptionsExample {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Create ChromeOptions instead of DesiredCapabilities
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true); // Enable insecure certificates
        options.addArguments("--disable-popup-blocking"); // Additional options

        WebDriver driver = new ChromeDriver(options); // Pass options to the driver
        driver.get("https://example.com");
        System.out.println("Page Title: " + driver.getTitle());
        driver.quit();
    }
}
```

* * *

### **Migrating to Selenium Grid in Selenium 4**

In Selenium Grid, `DesiredCapabilities` is replaced by `Options` or `MutableCapabilities`:

```java
// Remote WebDriver Example for Selenium Grid
ChromeOptions options = new ChromeOptions();
options.setBrowserVersion("114");
options.setPlatformName("WINDOWS");

// Connect to Selenium Grid
WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
```

* * *

### **Backward Compatibility**

Although `DesiredCapabilities` is deprecated, it is still available in Selenium 4 for backward compatibility but will likely be removed in future releases. It's advisable to start using the newer `Options` classes to future-proof your code.

* * *