db = new Mongo().getDB('user');
db.createCollection('user');

db.user.insert(
    {
        "username":"Admin",
        "password":"123",
        "email": "email.com",
        "name": "jelena",
        "surname": "petric",
        "address": {
            "streetNumber": "15",
            "streetName": "ulica",
            "postalCode": "78389",
            "town": "bp",
            "country": "srb"
        },
        "role":{
            "type":"ROLE_ADMIN"
        },
        "cancellationNumber" : 0,
        "isHighlightedHost" : false
    }
);

db.user.insert(
    {
        "username":"Regular",
        "password":"123",
        "email": "email.com",
        "name": "jelena",
        "surname": "petric",
        "address": {
            "streetNumber": "15",
            "streetName": "ulica",
            "postalCode": "78389",
            "town": "bp",
            "country": "srb"
        },
        "role":{
            "type":"ROLE_GUEST"
        },
        "cancellationNumber" : 0,
        "isHighlightedHost" : false
    }
);

db.user.insert(
    {
        "username":"Host Bojana",
        "password":"123",
        "email": "bojana@email.com",
        "name": "Bojana",
        "surname": "Zekanovic",
        "address": {
            "streetNumber": "15",
            "streetName": "ulica",
            "postalCode": "78389",
            "town": "bp",
            "country": "srb"
        },
        "role":{
            "type":"ROLE_ADMIN"
        },
        "cancellationNumber" : 0,
        "isHighlightedHost" : true,
        "grade":[
            {
            "createdAt": new ISODate("2023-03-24T10:01:43.161Z"),
            "reviewerId":null,
            "value":5
            }
        ],
        "avgGrade":5.0
    }
);

db.createCollection('role');
db.role.insert(
    {
        "type" : "ROLE_GUEST"
    }
);

db.role.insert(
    {
        "type" : "ROLE_ADMIN"
    }
);