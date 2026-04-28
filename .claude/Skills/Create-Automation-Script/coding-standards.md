# Coding Standards for Automation

Guidelines for writing clean, maintainable Java code within the TestingMint automation project.

## Naming Conventions
- **Classes**: PascalCase (e.g., `LoginPage`, `BookingTests`).
- **Methods**: camelCase (e.g., `loginToApplication`, `searchForFlight`).
- **Variables**: camelCase (e.g., `departureDate`, `passengerCount`).
- **Constants**: UPPER_SNAKE_CASE (e.g., `MAX_RETRY_ATTEMPTS`).

## Java Best Practices
- Use meaningful and descriptive names for all identifiers.
- Follow the DRY (Don't Repeat Yourself) principle.
- Keep methods short and focused on a single responsibility.
- Use `final` for variables that do not change.
- Prefer `List` and `Map` interfaces over concrete implementations in declarations.

## Documentation
- Use Javadoc for public methods in Page objects and Utility classes.
- Add brief comments for complex logic, but aim for self-documenting code.
