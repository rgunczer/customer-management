# customer-management
Yankee Zulu


## GET byId

```bash
 curl localhost:8080/customers/c65e528e-c5a2-42ee-8678-5f6570f4881a
```

 if piped by "jq"

```bash
  curl localhost:8080/customers/c65e528e-c5a2-42ee-8678-5f6570f4881a | jq
```

  the output is much nicer in terminal
```json
{
  "id": "c65e528e-c5a2-42ee-8678-5f6570f4881a",
  "name": "Alice Wonderland",
  "email": "alice@example.com",
  "annualSpend": 1500,
  "lastPurchaseDate": "2024-08-01T10:00:00",
  "tier": "Gold"
}
```

## GET byEmail

```bash
curl localhost:8080/customers?email=alice@example.com
```

## GET byName

```bash
curl http://localhost:8080/customers?name=Boba%20Fett
```

## POST

```bash
curl -X POST http://localhost:8080/customers \
  -H "Content-Type: application/json" \
  -d '{
        "name": "John Matrix",
        "email": "matrix@example.com",
        "annualSpend": 5500.75,
        "lastPurchaseDate": "2025-03-01T15:30:00"
      }'
```

## DELETE

```bash
 curl -X DELETE localhost:8080/customers/05818864-e18f-4f23-983c-bef0cd322ef7
```
