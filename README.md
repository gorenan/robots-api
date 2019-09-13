###Message API 
## Created by Renan Ovando
###Objectives:
- CRUD of bots and send messages.

### Technologies:
- Java 8
- Springboot
- JUnit and MockMVC
- MongoDB

## Endpoints:
###/bots
Methods allowed: `GET`, `POST`, `PUT`and `DELETE`.

###GET
Finds bot by id. Required fields: id;

Request Example (url):
/bots/5d7bbbb998e8720553eed5ff

Response (200-OK) Example:
``` javascript 
{
    "id": "5d7bbbb998e8720553eed5ff",
    "name": "GET Test"
}
```
###POST
Create a bot. Required fields: name;

Request Example:
``` javascript 
{
    "name": "POST Test"
}
```

Response (200-OK) Example:
``` javascript 
{
    "id": "5d7bbbb998e8720553eed5ff",
    "name": "POST Test"
}
```
###PUT
Update a bot. Required fields: id;

Request Example:
``` javascript 
{
    "id": "5d7bbbb998e8720553eed5ff",
	"name": "PUT Test"
}
```

Response (200-OK) Example:
``` javascript 
{
    "id": "5d7bbbb998e8720553eed5ff",
    "name": "PUT Test"
}
```
###DELETE
Update a bot. Required fields: id;

Request Example:
``` javascript 
{
    "id": "5d7bbbb998e8720553eed5ff",
	"name": "PUT Test"
}
```

Response (200-OK) Example:
``` javascript 
{
    "id": "5d7bbbb998e8720553eed5ff",
    "name": "PUT Test"
}
```
###/messages
Methods allowed: `GET` and `POST`.

###GET
Finds message by id. Required fields: id;

Request Example (url):
/messages/5d7bc5db6d74512aa0c02db6 

Response (200-OK) Example:
``` javascript 
{
    "id": "5d7bc5db6d74512aa0c02db6",
    "conversationId": "5d7bc5db6d74512aa0c02db5",
    "timestamp": "2019-09-13T16:37:47.512+0000",
    "from": "From",
    "to": "To",
    "text": "Text"
}
```
###GET
Finds conversations by id. Required fields: conversationId;

Request Example (url):
/messages?conversationId=5d7bbbb998e8720553eed5ff

Response (200-OK) Example:
``` javascript 
[
	{
		"id": "5d7bc5db6d74512aa0c02db6",
		"conversationId": "5d7bc5db6d74512aa0c02db5",
		"timestamp": "2019-09-13T16:37:47.512+0000",
		"from": "From",
		"to": "To",
		"text": "Text"
	},
	{
		"id": "5d7bc5db6d74512aa0c02c06",
		"conversationId": "5d7bc5db6d74512aa0c02db5",
		"timestamp": "2019-09-13T16:47:47.512+0000",
		"from": "From 2",
		"to": "To 2",
		"text": "Text 2"
	}
]
```
###POST
Create a message AND/OR conversation. 
Required fields: 
- to;
- from;
- text;

Optional Field:
- conversationId;

if you want to initialize a conversation, you should to request as the example bellow:
``` javascript 
 {
    "from": "From",
    "to": "To",
    "text": "Text"
}
```
So, it will response, returning the *conversationID*
Response (200-OK) Example:
``` javascript 
{
    "id": "5d7bc5db6d74512aa0c02db6",
    "conversationId": "5d7bc5db6d74512aa0c02db5",
    "timestamp": "2019-09-13T16:37:47.512+0000",
    "from": "From",
    "to": "To",
    "text": "Text"
}
```
so you can continue this conversation using the *conversationId* :
```javascript 
{
		"conversationId": "5d7bc5db6d74512aa0c02db5",
		"from": "From 2",
		"to": "To 2",
		"text": "Text 2"
	}
````
then, the response is:

```javascript 
{
		"id": "5d7bc5db6d74512aa0c02c06",
		"conversationId": "5d7bc5db6d74512aa0c02db5",
		"timestamp": "2019-09-13T16:47:47.512+0000",
		"from": "From 2",
		"to": "To 2",
		"text": "Text 2"
	}
````
