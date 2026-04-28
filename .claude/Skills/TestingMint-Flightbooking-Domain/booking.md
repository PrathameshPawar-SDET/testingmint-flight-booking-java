# Booking Process Domain Knowledge

Knowledge related to the overall flight booking lifecycle.

## Workflow Stages
1. **Search**: Find available flights.
2. **Select**: Choose specific flight options.
3. **Passenger Details**: Enter traveler information.
4. **Seat Selection**: Choose seats on the aircraft.
5. **Payment**: Process transaction.
6. **Confirmation**: Receive PNR and e-ticket.

## Business Logic
- **PNR Generation**: Unique Passenger Name Record for every successful booking.
- **Session Timeout**: Bookings must be completed within a specific timeframe to hold the fare.
- **Inventory Management**: Seats are temporarily held during the selection process.
