# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Test Commands

```bash
# Run all tests
mvn clean test

# Run a specific test class
mvn test -Dtest=BookingTests
mvn test -Dtest=SearchTests

# Run a single test method
mvn test -Dtest=BookingTests#testE2EFlightBooking

# Run via TestNG suite
mvn surefire:test
```

TestNG suite is configured at `src/test/resources/testng.xml`. Reports are generated at `/reports/ExtentReport.html` after each run.

## Architecture Overview

This is a **Selenium 4 + TestNG** end-to-end test suite for a React-based flight booking web app. It uses the Page Object Model (POM) pattern.

### Layer Structure

```
BaseTest (driver lifecycle)
    └── Test classes (SearchTests, BookingTests)
            └── Page classes (HomePage, SearchResultsPage, SeatSelectionPage, LoginPage, PassengerDetailsPage)
                    └── BasePage (shared waits and interaction helpers)
```

**`BaseTest`** — sets up and tears down Chrome/Firefox WebDriver via WebDriverManager. Browser and URL are read from `src/test/resources/config.properties`. Each `@Test` method gets a fresh browser session.

**`BasePage`** — all page classes extend this. Key methods:
- `click()`, `type()` — use explicit waits before acting
- `setDateInput()` — uses JavaScript value setters + synthetic React events to handle native `<input type="date">` in React controlled components
- `type()` internally fires character-by-character with 50ms delays to trigger React `onChange` handlers

**Page classes** — locators use `data-testid` attributes where possible, falling back to CSS selectors and XPath.

### Configuration

`src/test/resources/config.properties`:
```properties
url=https://app.testingmint.com/projects/testingmint-flight-booking/
browser=chrome
timeout=10
```

`ConfigReader` loads this at static initialization; `ExtentReportManager` is a singleton wired via `TestListener` (registered in `testng.xml`).

### Key Dependencies

| Dependency | Version | Role |
|---|---|---|
| selenium-java | 4.18.1 | Browser automation |
| testng | 7.9.0 | Test framework |
| webdrivermanager | 5.7.0 | Automatic driver binary management |
| extentreports | 5.1.1 | HTML test reporting |

### Date Input Handling

HTML5 date inputs inside React components require special treatment. `BasePage.setDateInput()` injects the value via `JavascriptExecutor`, then dispatches `input` and `change` events. `HomePage.normalizeDate()` accepts multiple date string formats (ISO, `MMM d yyyy`, `MMMM d yyyy`, etc.) and normalizes them to `yyyy-MM-dd` before passing to `setDateInput()`.

### Test Flow (E2E Booking)

`BookingTests.testE2EFlightBooking()` exercises the full user journey:
1. `HomePage.searchFlights()` — fills origin, destination, date, passengers; clicks Search
2. `SearchResultsPage.selectFirstFlight()` — clicks "Select Seats" on the first result
3. `SeatSelectionPage.selectSeat()` — picks a seat by number, skips already-booked seats; then `clickProceed()`
4. `LoginPage.login()` — enters credentials from config
5. `PassengerDetailsPage.fillPassengerDetails()` + `clickConfirmBooking()` — fills form and asserts a booking ID appears
