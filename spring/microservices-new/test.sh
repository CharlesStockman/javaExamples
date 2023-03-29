curl -i http://localhost:8081/api/order \
  -H "Accept: application/json" \
  -H "Content-Type:application/json" \
  -X POST --data  '{ "orderLineItemsDto":[ { "skuCode": "iphone_13", "price": 1200, "quantity":1 } ] }'
