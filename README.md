# Spring Boot Pagination

## Overview
Spring Boot provides a functionality to paginate huge amounts of data out of the box. The size of each page can be
configured. All needed values to handle pagination is always returned by the REST responses.

## Implementation
This example set the page size to 20 (in *application.properties*). Furthermore, sorting is defaulted to ascending 
on a specific field (here *name* of entity *item*).

The page response looks like this example:
```json
{
    "content": [
        {
            "name": "B",
            "description": "m"
        },
        
        // 18 more items here
      
        {
            "name": "BpTTbzEfdzekdoaucGfDfQpH",
            "description": "OrTaSWaNAqzaEDxGfaRtHvJcWSzcTp"
        }
    ],
    "pageable": {
        "pageNumber": 1,
        "pageSize": 20,
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 20,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 50,
    "totalElements": 1000,
    "last": false,
    "size": 20,
    "number": 1,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "first": false,
    "numberOfElements": 20,
    "empty": false
}
```
The bottom part of the response contains the pagination information needed to page the data.

## Testing
For testing, a set of random items is generated. 

To fetch the second page of all items, run following request.
```shell
curl --location 'http://localhost:18084/api/items/1'
```
Note that the page number start at zero. 
