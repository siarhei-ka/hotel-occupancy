# Hotel Room Occupancy Optimizer

## Features
- Optimizes room assignment and upgrade for premium/economy
- REST API `/occupancy` (POST)
- Includes unit tests

## Build & Run

```bash
chmod +x run.sh
./run.sh
```

App runs on: `http://localhost:8080`

## API Usage

**POST** `/occupancy`

**Input:**
```json
{
  "premiumRooms": 7,
  "economyRooms": 5,
  "potentialGuests": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
}
```

**Output:**
```json
{
  "usagePremium": 6,
  "revenuePremium": 1054.0,
  "usageEconomy": 4,
  "revenueEconomy": 189.99
}
```

## Run Tests
```bash
./mvnw test
```
