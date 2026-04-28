# Test Structure & TestNG

Guidelines for structuring test classes and using TestNG features.

## Annotations
- `@BeforeMethod`: Setup logic before every test (e.g., navigate to home).
- `@AfterMethod`: Cleanup after every test.
- `@Test`: Marks a method as a test case.
- `@DataProvider`: Used for data-driven testing.

## Groups & Suites
- Use `groups` (e.g., `smoke`, `regression`) to categorize tests in `@Test(groups = {"smoke"})`.
- Manage execution via `testng.xml` in `src/test/resources`.

## Assertions Style
- Use `Assert.assertEquals()`, `Assert.assertTrue()`, etc., for validation.
- Provide descriptive error messages in assertions: `Assert.assertTrue(isDisplayed, "Login button should be visible after logout");`.
