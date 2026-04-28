# Framework Architecture (POM)

The project follows the Page Object Model (POM) design pattern to enhance test maintenance and reduce code duplication.

## Main Components (`src/main/java`)
- **`BasePage`**: Contains the Playwright `Page` instance and common wrapper methods for interaction (click, type, wait).
- **Page Objects**: Located in `com.testingmint.pages`. Each class represents a web page (e.g., `HomePage`, `SearchResultsPage`).
- **Utilities**: Located in `com.testingmint.utils`. Contains helper classes like `ConfigReader` and `ExtentReportManager`.

## Test Components (`src/test/java`)
- **`BaseTest`**: Handles Playwright initialization, browser context setup, and teardown.
- **Test Classes**: Located in `com.testingmint.tests`. Focus on test logic and assertions using Page Objects.

## Interaction Flow
1. Test Method calls a Page Object method.
2. Page Object method uses Playwright locators to interact with the UI.
3. Page Object returns a new Page Object or a result to the Test Method.
4. Test Method performs assertions on the result.
