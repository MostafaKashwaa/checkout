## Checkout

### General:
---

This Service was implemented to be used serverlessly, without the need to interact with any data storage to save the lambda run time.
Other Services should be implemented to manage the user-basket, orders, user-profiles, stock, etc...

It deals with the paymob payment gate to register an order, and generate an iFrame-Url for the client to deal directly with the payment gate,
and without the user card data ever touches the server. Later the payment gate sends updates to a webhook that can be implemented to recieve the
payment status updates.

### How to Test and Run:
---
- add your paymob credintials in a properties file as follows:

```
paymob.api.key=ZXlKMGVYQWlPaUpL......
paymob.integration.card=16.....
paymob.iframe.id=32....
```

in addition to these urls.
```
paymob.auth.url=https://accept.paymob.com/api/auth/tokens
paymob.reg.url=https://accept.paymob.com/api/ecommerce/orders
paymob.pay.url=https://accept.paymob.com/api/acceptance/payment_keys
```

- run the following command from the checkout root directory:
```commandline
./gradlew bootRun
```

- to run the test suite execute the following command:
```commandline
./gradlew test
```

---
### End Points:
---

1. validate
---

- Validates the order and generates a report that can be used without interacting with the payment gate.

- The `ValidateTransation` use-case that validates the Order, uses a list of three validators, all implement the `BasketValidator` interface.
So, to change/extend the validation logic, just implement your own validator and add/remove validators in the `Basket`'s list of 
validators in the use-case (No need to change any logic in the domain objects).

|**Method**|`POST`|
|----|---
|**URL:** |`http://127.0.0.1:8080/validate`|
|**content-type:**|`application/json`|


**Sample request body:**
```json
{
    "items": [
        {
            "name": "item1",
            "price": 15.99,
            "available": true
        },
        {
            "name": "item2",
            "price": 85.99,
            "available": true
        }
    ]
}
```


**Sample *curl* request**
```commandline
curl http://127.0.0.1:8080/validate -X POST -H "Content-Type: application/json" -d '{"items": [{"name": "item1", "price": 15.99, "available": true},{"name": "item2", "price": 85.99, "available": true}]}'
```


**Sample successful response**
```json
{
    "total": "101.98",
    "code": 200,
    "message": "item1\t15.99\tavailable\nitem2\t85.99\tavailable\nTotal: 101.98",
    "items": [
        {
            "name": "item1",
            "price": 15.99,
            "available": true
        },
        {
            "name": "item2",
            "price": 85.99,
            "available": true
        }
    ],
    "status": "success"
}
```


**Sample failed response with code 422**
```json
{
    "total": "85.99",
    "code": 422,
    "message": "item1 is currently not available\n",
    "items": [
        {
            "name": "item1",
            "price": 15.99,
            "available": false
        },
        {
            "name": "item2",
            "price": 85.99,
            "available": true
        }
    ],
    "status": "Unprocessable"
}
```

---
2. checkout
---

- Creates an order in the payment portal 
- Gets a payment key for the card channel (channel can be altered by changing the `paymob.integration.card` in the properties file).
- Returns the created order_id and an iFrame_Url to process the payment.

The order_id can be forwarded to the order-management-service to be saved as pending till the payment gate respond with other updates.

The client should use this end-point to get the iFrame_Url and use it to proceed with the payment. 

The payment gate will, later, respond to the service that implements the webhook to recieve the payment updates.

|**Method**|`POST`|
|----|---
|**URL:** |`http://127.0.0.1:8080/checkout`|
|**content-type:**|`application/json`|


**Sample request body:**
```json
{
    "items": [
        {
            "name": "item1",
            "price": 15.99,
            "available": true
        },
        {
            "name": "item2",
            "price": 85.99,
            "available": true
        }
    ]
}
```


**Sample *curl* request**
```commandline
curl http://127.0.0.1:8080/checkout -X POST -H "Content-Type: application/json" -d '{"items": [{"name": "item1", "price": 15.99, "available": true},{"name": "item2", "price": 85.99, "available": true}]}'
```


**Sample Successful response**
```json
{
    "frameUrl": "https://accept.paymob.com/api/acceptance/iframes/3.....?payment_token=ZXlKaGJHY2lPaUpJVXp.........w==",
    "orderId": 26868075
}
```
