# Create Test Case Skill

This skill provides a structured framework for generating high-quality test cases for the TestingMint flight booking application.

---
**Configuration**
- `user_invocation: false`
---

## High-Level Capabilities

### 1. Requirements Analysis
Analyze user stories and functional requirements to derive testable scenarios.
- [Design Guidelines](./guidelines.md)

### 2. Standardized Documentation
Generate test cases using approved templates (Standard or BDD/Gherkin).
- [Templates](./templates.md)

### 3. Exhaustive Testing Strategies
Apply techniques like Boundary Value Analysis (BVA) and Equivalence Partitioning (EP) to identify edge cases.
- [Edge Cases & Boundaries](./edge-cases.md)

## Constraints & Clarity Rules
- **No Ambiguity**: Avoid words like "check", "verify", or "test" in steps unless followed by a specific action and expected result.
- **Data Driven**: Always specify the type of data (Valid, Invalid, Null) to be used.
- **End-to-End focus**: Ensure cases cover the transition between modules (e.g., Search -> Select -> Passenger).

## Usage
Use this skill when:
- Designing new manual test cases.
- Generating Gherkin scenarios for automation.
- Reviewing existing test suites for coverage gaps.
