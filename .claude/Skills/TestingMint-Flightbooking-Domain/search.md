# Flight Search Domain Knowledge

Knowledge related to searching for flights in the TestingMint application.

## Key Search Parameters
- **Trip Type**: One-way, Round-trip, Multi-city.
- **Origin & Destination**: Airport codes or city names.
- **Dates**: Departure and return dates (date format handling).
- **Passengers**: Number of adults, children, and infants.
- **Cabin Class**: Economy, Premium Economy, Business, First Class.

## Validation Rules
- Return date cannot be before departure date.
- Origin and destination cannot be the same.
- Minimum one adult required for booking.
