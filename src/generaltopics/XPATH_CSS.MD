# XPATH
Xpath stands for XML path language which is a syntax for navigating through elements and attributes in an XML or HTML document. In the context of Selenium WebDriver, it is commonly used to locate web elements on a page for interaction (e.g., clicking, entering text, reading values). XPath provides flexibility in finding elements with complex conditions and hierarchical structures that other locators (like ID, name, class) may not capture as easily.

**Types of XPath**

**Absolute XPath**: Starts from the root element and follows a full path to the desired element.<br>
Example: ```/html/body/div[1]/header/nav/a<br>```<br>
Drawback: Prone to breaking if the structure of the page changes.

**Relative XPath**: Starts with // and finds an element from anywhere in the document.<br>
Example:``` //div[@class='example']/a[text()='Click Me']```<br>
Advantage: More flexible and reliable than absolute XPath.

Basic Syntax

//tagname[@attribute='value'] — Finds elements by tag and attribute.
Example: //input[@id='username']
Using Functions

contains(): Partial match for attribute values.<br>
Example: //button[contains(@class, 'submit')]<br>
text(): Matches based on the text content.<br>
Example: //p[text()='Welcome']

Using Logical Operators

and, or — Combine multiple conditions.
Example: //input[@type='text' and @name='email']
XPath Axes

parent, child, ancestor, descendant, following, preceding, etc., allow more specific selection of elements relative to others.
Example: //h3/parent::div — Selects the parent div of an h3 element.

# CSS Selector