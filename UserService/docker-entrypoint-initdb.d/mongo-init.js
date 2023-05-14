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
        "cancellationNumber" : 0
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
        "cancellationNumber" : 0
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