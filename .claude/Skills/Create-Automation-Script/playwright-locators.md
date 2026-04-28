# Playwright & Locators Best Practices

Strategies for effective element identification and interaction using Playwright.

## Recommended Locators
- **Role**: `page.getByRole("button", new Page.GetByRoleOptions().setName("Search"))` (Preferred for accessibility).
- **Text**: `page.getByText("Welcome")`.
- **Label**: `page.getByLabel("Username")`.
- **Placeholder**: `page.getByPlaceholder("Enter city")`.
- **Test ID**: `page.getByTestId("submit-btn")` (Use if available).
- **CSS/XPath**: Use as a last resort when semantic locators are insufficient.

## Synchronization & Waiting
- Playwright has "Auto-waiting," but use `waitForSelector` or `waitForLoadState` when handling complex transitions.
- Avoid `Thread.sleep()`. Use Playwright's built-in waiting mechanisms.

## Assertions
- Use Playwright's web-first assertions: `assertThat(page.getByText("Success")).isVisible();`.
- Combine with TestNG assertions for non-UI validation.
