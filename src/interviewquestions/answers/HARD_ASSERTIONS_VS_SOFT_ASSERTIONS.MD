### Hard Assertions vs. Soft Assertions in Selenium Java

Assertions are used in Selenium tests to verify expected outcomes. Java provides two types of assertions: **Hard Assertions** and **Soft Assertions**.

* * *

### 🔹 **Hard Assertions**

*   Provided by **JUnit** or **TestNG (`Assert` class)**.
*   **Stops** test execution immediately if the assertion fails.
*   Ensures test cases fail fast without executing subsequent steps.
*   Used when test steps depend on a condition being met.

#### Example:

```java
import org.testng.Assert;
import org.testng.annotations.Test;

public class HardAssertionExample {
    @Test
    public void testHardAssertion() {
        System.out.println("Step 1: Execution starts");
        Assert.assertEquals("Hello", "World"); // ❌ Fails here, test execution stops
        System.out.println("Step 2: This will not execute");
    }
}
```

📌 **Key Takeaways**:

*   Stops test execution on failure.
*   Used when a failed step should prevent further execution.

* * *

### 🔹 **Soft Assertions**

*   Provided by **TestNG (`SoftAssert` class)**.
*   **Does not stop** test execution on failure.
*   Executes all test steps and collects assertion failures.
*   Used when multiple conditions need to be verified before failing the test.

#### Example:

```java
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertionExample {
    @Test
    public void testSoftAssertion() {
        SoftAssert softAssert = new SoftAssert();

        System.out.println("Step 1: Execution starts");
        softAssert.assertEquals("Hello", "World"); // ❌ Fails but test continues
        System.out.println("Step 2: This still executes");
        softAssert.assertTrue(5 > 10, "Condition failed"); // ❌ Another failure

        softAssert.assertAll(); // 🔴 Reports all assertion failures at the end
    }
}
```

📌 **Key Takeaways**:

*   Does **not** stop execution immediately.
*   **Must call** `softAssert.assertAll();` at the end to report failures.
*   Useful when verifying multiple independent conditions.

* * *

### 🔥 **When to Use What?**

**Scenario**

**Use**

|              Scenario             |       Use      |
|:---------------------------------:|:--------------:|
| Critical failure should stop test | Hard Assertion |
| Multiple independent checks       | Soft Assertion |