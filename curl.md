## Get all meal

### Request

    curl -X GET http://localhost:8080/topjava/rest/meals/

## Get meal by id

### Request

    curl -X GET http://localhost:8080/topjava/rest/meals/100002
    
## Delete meal by id
    
### Request

    curl -X DELETE http://localhost:8080/topjava/rest/meals/100002
    
## Update meal by id

### Request


## Get meal between dates

### Request

curl -X GET  'http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-30&startTime=00:00:00&endDate=2020-01-31&endTime=22:00:00'