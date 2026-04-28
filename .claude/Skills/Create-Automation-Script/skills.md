# Create Automation Script Skill

This skill provides expert guidance for developing robust, maintainable, and idiomatic automation scripts using Java, Playwright, and TestNG.

---
**Configuration**
- `user_invocation: false`
---

## High-Level Capabilities

### 1. Page Object Development
Create and maintain Page Object classes that encapsulate UI interactions and locators.
- [Framework Architecture](./framework-architecture.md)
- [Locators Best Practices](./playwright-locators.md)

### 2. Test Case Implementation
Translate manual test steps or Gherkin scenarios into executable Java tests.
- [Test Structure & TestNG](./test-structure.md)

### 3. Clean Code & Maintenance
Apply industry-standard coding practices to ensure the automation suite remains scalable.
- [Coding Standards](./coding-standards.md)

## Constraints
- **Strict POM**: Never put Playwright interaction logic directly in Test classes.
- **No Hardcoding**: Use `ConfigReader` or Data Providers for environment and test data.
- **Explicit Assertions**: Every test must have at least one clear assertion.

## Usage
Use this skill when:
- Implementing a new automated test case.
- Creating or updating Page Object classes.
- Refactoring existing automation code.
- Debugging automation script failures.
