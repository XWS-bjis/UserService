db = new Mongo().getDB('user');
db.createCollection('user');

db.user.insert(
    {
        "username":"Admin",
        "password":"123",
        "email": "jelena@email.com",
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
        "email": "sandra@email.com",
        "name": "sandra",
        "surname": "jovanovic",
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
        "isHighlightedHost" : true
    }
);

db.user.insert(
    {
        "username":"Host Isidora",
        "password":"123",
        "email": "isidora@email.com",
        "name": "Isidora",
        "surname": "Poznanovic",
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