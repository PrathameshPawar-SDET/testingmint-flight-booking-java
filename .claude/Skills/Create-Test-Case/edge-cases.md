# Edge Cases & Boundary Analysis

Specific scenarios to ensure robustness in the flight booking system.

## Search Edge Cases
- **Last Minute Booking**: Searching for a flight departing in less than 2 hours.
- **Future Booking**: Searching for a flight more than 1 year in advance.
- **Maximum Passengers**: Booking for 9+ passengers in a single transaction.
- **Leap Year**: Booking on Feb 29th.

## Passenger Detail Edge Cases
- **Name Length**: Very long first/last names (hyphenated or multiple names).
- **Special Characters**: Accents (e.g., José) or hyphens in names.
- **Age Boundaries**: Infant turning 2 during the trip (Child status change).

## Technical Edge Cases
- **Session Expiry**: Completing a booking after the 15-minute hold time.
- **Concurrent Booking**: Two users selecting the last available seat simultaneously.
- **Network Interruption**: Disconnecting during the payment processing.
