# XPATH
In Selenium, XPath (XML Path Language) is a syntax for navigating through elements and attributes in an XML or HTML document. In the context of Selenium WebDriver, it is commonly used to locate web elements on a page for interaction (e.g., clicking, entering text, reading values). XPath provides flexibility in finding elements with complex conditions and hierarchical structures that other locators (like ID, name, class) may not capture as easily.

### Types of XPath

1.  **Absolute XPath**: Starts from the root element and follows a full path to the desired element.

    *   Example: `/html/body/div[1]/header/nav/a`
    *   **Drawback**: Prone to breaking if the structure of the page changes.
2.  **Relative XPath**: Starts with `//` and finds an element from anywhere in the document.

    *   Example: `//div[@class='example']/a[text()='Click Me']`
    *   **Advantage**: More flexible and reliable than absolute XPath.

### Syntax Examples

1.  **Basic Syntax**

    *   `//tagname[@attribute='value']` — Finds elements by tag and attribute.
        *   Example: `//input[@id='username']`
2.  **Using Functions**

    *   `contains()`: Partial match for attribute values.
        *   Example: `//button[contains(@class, 'submit')]`
    *   `text()`: Matches based on the text content.
        *   Example: `//p[text()='Welcome']`
3.  **Using Logical Operators**

    *   `and`, `or` — Combine multiple conditions.
        *   Example: `//input[@type='text' and @name='email']`
4.  **XPath Axes**

    *   `parent`, `child`, `ancestor`, `descendant`, `following`, `preceding`, etc., allow more specific selection of elements relative to others.
        *   Example: `//h3/parent::div` — Selects the parent `div` of an `h3` element.

### Example Usage in Selenium (Java)

```java
WebElement element = driver.findElement(By.xpath("//input[@type='text' and @name='username']"));
element.sendKeys("YourUsername");
```

Using XPath effectively can make your locators more powerful and robust, especially for complex page structures.

# CSS Selector