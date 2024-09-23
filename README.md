## ⛄ Get started with Billing service

### Start service Locally - 
PORT - 8080

Swagger - Billing Service
```
http://localhost:8080/swagger-ui/index.html#/
```

Curl

### Create Bill
billId is optional
```
curl -X 'POST' \
  'http://localhost:8080/api/bill/' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "billId": 0,
  "customerId": 0,
  "amount": 0,
  "status": "PAID"
}'
```

### Get bill by bill id
```
curl -X 'GET' \
  'http://localhost:8080/api/bill/1' \
  -H 'accept: */*'
```

### Get all bills
```
curl -X 'GET' \
  'http://localhost:8080/api/bill/' \
  -H 'accept: */*'
```


### Get bills by status
status can be DUE/PAID
```
curl -X 'GET' \
  'http://localhost:8080/api/bill/status/DUE' \
  -H 'accept: */*'
```
