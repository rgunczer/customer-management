# customer-management

**Take-Home Project for EA:NorthAmerica Project**

Application is developed in VSCode with VIM keybindings and Hyper color theme

to  run the app in VSCode install the "Extension pack for Java" from Microsoft it will include other extensions

Then open the `CustomerApiApplication.java` file and click on Run overlay above the main method

System will start listening on default 8080 port

On successful start look for a console message "READY-2-SERVE"

To run it from terminal use the "classical" command

```bash
mvn spring-boot:run
```


## Access the H2 database
```
http://localhost:8080/h2-console
```

## GET byId (note the UUID is re-generated each time app starts)

```bash
curl localhost:8080/customers/c65e528e-c5a2-42ee-8678-5f6570f4881a
```

 if piped by `jq`

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

## PUT
```bash
curl -X PUT http://localhost:8080/customers/25463d35-38a7-4bec-947b-a3cbe108043a \
-H "Content-Type: application/json" \
-d '{
  "name": "Robert Gunczer",
  "email": "robert@example.com",
  "annualSpend": 3500.75,
  "lastPurchaseDate": "2025-06-01T10:00:00"
}'
```

## DELETE

```bash
 curl -X DELETE localhost:8080/customers/05818864-e18f-4f23-983c-bef0cd322ef7
```


## Open API Spec

Spring can generate this "onthefly" by visiting this url

```
http://localhost:8080/swagger-ui.html
```
