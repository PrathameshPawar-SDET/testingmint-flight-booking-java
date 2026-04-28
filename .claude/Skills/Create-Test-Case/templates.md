# Test Case Templates

Standardized formats for documenting test cases.

## Template 1: Step-by-Step (Standard)
| ID | Step Description | Expected Result |
|----|------------------|-----------------|
| 01 | Navigate to Search Page | Home page displays with search form |
| 02 | Select 'One Way' trip type | 'Return Date' field is disabled |
| 03 | Enter Origin 'SFO' | Origin field accepts 'SFO' |

## Template 2: Gherkin (BDD)
**Scenario**: Successful One-Way Flight Search
- **Given** I am on the TestingMint home page
- **And** I have selected 'One Way' trip type
- **When** I enter valid origin, destination, and departure date
- **And** I click the 'Search' button
- **Then** I should see a list of available flights for the selected date
