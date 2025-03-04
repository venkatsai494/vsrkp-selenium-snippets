**Shadow DOM in Selenium with Java**
====================================

**1\. What is Shadow DOM?**
---------------------------

*   **Shadow DOM** is a web standard that allows **encapsulation** of HTML, CSS, and JavaScript inside a web component.
*   Elements inside a **shadow root** are not accessible via `document.querySelector()` or standard Selenium locators.
*   You can recognize a **shadow DOM** by checking for the `<shadow-root>` element in the browser's Developer Tools.

**2\. Identifying Shadow DOM Elements**
---------------------------------------

*   Inspect the element in DevTools (`F12` → Elements tab).
*   Look for **`#shadow-root (open)`** or **`#shadow-root (closed)`** under the parent element.
    *   **Open Shadow DOM**: Accessible via JavaScript and Selenium.
    *   **Closed Shadow DOM**: Not accessible directly (requires JavaScript hacks or alternative methods).

**3\. Locating Shadow DOM Elements in Selenium (Java)**
-------------------------------------------------------

### **A. Using JavaScript Executor**

Since Selenium cannot interact directly with elements inside a shadow DOM, **JavaScript execution** is required.

#### **Example 1: Accessing a Single-Level Shadow DOM**

```java
JavascriptExecutor js = (JavascriptExecutor) driver;
WebElement shadowHost = driver.findElement(By.cssSelector("css-selector-of-shadow-host"));

// Get shadow root using JavaScript
WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);

// Find an element inside the shadow DOM
WebElement elementInsideShadowDOM = shadowRoot.findElement(By.cssSelector("css-selector-inside-shadow"));
elementInsideShadowDOM.click();
```

#### **Example 2: Accessing Multi-Level (Nested) Shadow DOM**

If a shadow DOM contains another shadow DOM inside it, navigate through each layer.

```java
WebElement shadowHost1 = driver.findElement(By.cssSelector("shadow-host-1"));
WebElement shadowRoot1 = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost1);

WebElement shadowHost2 = shadowRoot1.findElement(By.cssSelector("shadow-host-2"));
WebElement shadowRoot2 = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost2);

// Now access the element inside the second shadow DOM
WebElement nestedElement = shadowRoot2.findElement(By.cssSelector("nested-element-selector"));
nestedElement.click();
```

* * *

**4\. Handling Shadow DOM in Dynamic Pages**
--------------------------------------------

*   If the shadow DOM is **dynamically created**, wait until it is attached to the DOM using **Explicit Wait**.

### **Example: Using WebDriverWait**

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
WebElement shadowHost = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("shadow-host-selector")));

WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);
WebElement shadowElement = shadowRoot.findElement(By.cssSelector("element-inside-shadow"));
shadowElement.click();
```

* * *

**5\. Handling Input Fields inside Shadow DOM**
-----------------------------------------------

Use JavaScript to **set values** inside a shadow DOM input field:

```java
js.executeScript("arguments[0].value='your text'", shadowElement);
```

or using standard Selenium methods:

```java
shadowElement.sendKeys("your text");
```

* * *

**6\. Limitations of Selenium with Shadow DOM**
-----------------------------------------------

**Limitation**

|                        Limitation                        |                 Solution                 |
|:--------------------------------------------------------:|:----------------------------------------:|
| Standard Selenium locators don’t work inside Shadow DOM  | Use JavaScript Executor                  |
| Cannot interact with elements inside a Closed Shadow DOM | Requires browser extensions or API hooks |
| Dynamic shadow elements may not load instantly           | Use Explicit Waits                       |
| Shadow DOM may have event-driven interactions            | Use JavaScript-based event triggers      |

* * *

**7\. Tools to Debug Shadow DOM**
---------------------------------

*   **Chrome DevTools** → Open Elements tab and inspect `#shadow-root`.
*   **JavaScript Console**:

    ```js
    document.querySelector("shadow-host-selector").shadowRoot
    ```

*   **Playwright / Puppeteer**: Native support for Shadow DOM, but not available in Selenium.

* * *

**8\. Real-world Example**
--------------------------

### **Handling a Shadow DOM Search Box**

```java
// Locate the shadow host
WebElement shadowHost = driver.findElement(By.cssSelector("search-box"));

// Get the shadow root
WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);

// Find and interact with an input field inside the shadow root
WebElement searchField = shadowRoot.findElement(By.cssSelector("input"));
searchField.sendKeys("Selenium Shadow DOM");
```

* * *

**Key Takeaways**
-----------------

✔ **Shadow DOM encapsulates elements**, making them inaccessible by default to Selenium.  
✔ Use **JavaScript Executor** to access **open shadow DOM** elements.  
✔ For **multi-level Shadow DOM**, traverse each layer using `shadowRoot`.  
✔ Use **Explicit Waits** for dynamically loaded shadow elements.  
✔ **Closed Shadow DOM** is not accessible via Selenium.
