# Manage Properties System with Spring Boot and JPA
### Miguel Motta

## Contents
- [Project Summary](#project-summary)
- [Requirements](#requirements)
- [Class Design](#class-design)
- [Setup](#setup)
- [API Endpoints](#api-endpoints)
- [Deployment](#deployment)
- [Video demonstration](#video-demonstration)

## Project Summary

This project is a simple Spring Boot application that manages a properties system using JPA 
(Java Persistence API). It allows you to perform CRUD (Create, Read, Update, Delete) 
operations on properties.

## Requirements
- Java 11 or higher
- Maven
- A database (H2, MySQL, PostgreSQL, etc.)
- Spring Boot
- JPA (Hibernate)
- Docker (optional, for containerization)
- Postman (optional, for API testing)
- Lombok (optional, for reducing boilerplate code)


## System Architecture



## Class Design

![](assets/class-diagram-arep-05.drawio.png)

[See the diagram on draw.io](https://drive.google.com/file/d/1c-YKosibjRvDxHWE9W2LjTsW9SHU5ynl/view?usp=sharing)

[See the Image](assets/class-diagram-arep-05.drawio.png)

## Setup
1. Clone the repository:
```bash
   git clone https://github.com/MIGUEL-MOTTA-U/arep-t1-WS-5
    cd arep-t1-WS-5
```

2. Configure the database connection in `src/main/resources/application.properties`:
```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
```

I made a configuration to use a `.env` file to manage environment variables, you can guide from the `.env.example` file.
````dotenv
    MY_DB_URL=jdbc:mysql://{my_db_host}:{my_db_port}/{my_database_name}?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    MY_DB_ROOT_PASSWORD={my_db_root_password}
    MY_DATABASE={my_database_name}
    MY_DB_USER={my_db_user}
    MY_DB_USER_PASSWORD={my_db_user_password}
````
3. Run docker-compose to set up the database (if using Docker):
```bash
   docker-compose -f docker-compose.dev.yml up -d
```

4. Build and run the application:
```bash
   mvn clean package
   mvn spring-boot:run
```

## API Endpoints
The application exposes the following RESTful API endpoints:
- `GET api/v1/properties`: Retrieve all properties

Example of response:
```json
{
    "content": [
        {
            "propertyEntity": "HOUSE",
            "id": "41bf1288-d314-40b6-ae61-da70b967b328",
            "type": "HOUSE",
            "address": "Cra 15 # 123-45",
            "city": "Bogotá",
            "active": true,
            "price": 3.5E8,
            "description": "Hermosa casa familiar en zona residencial.",
            "name": "Casa Los Nogales",
            "rooms": 4,
            "bathrooms": 3,
            "parkingLots": 2,
            "areaSquareMeters": 120.5,
            "owner": "juan.perez@example.com"
        }
    ],
    "page": {
        "size": 10,
        "number": 0,
        "totalElements": 1,
        "totalPages": 1
    }
}
```

You can use filtering, sorting, and pagination with the `GET api/v1/properties` endpoint by adding query parameters:

- `page`: Page number (default is 0)
- `size`: Number of records per page (default is 10)
- `adress`: Sort direction (asc or desc, default is asc)
- `city`: Filter by city's name
- `type`: Filter by property type (e.g., APARTMENT, HOUSE, COMMERCIAL)
- `minPrice`: Minimum price filter
- `maxPrice`: Maximum price filter
- `minAreaSquareMeters`: Minimum size filter in square meters
- `maxAreaSquareMeters`: Maximum size filter in square meters
- `name`: Filter by name of the property (partial match)



- `GET api/v1/properties/{id}`: Retrieve a property by ID

Example of response:
````json
{
    "propertyEntity": "HOUSE",
    "id": "90a66872-c5e7-4d6b-a37f-58ea5cf960d9",
    "type": "HOUSE",
    "address": "Cra 15 # 123-45",
    "city": "Bogotá",
    "active": true,
    "price": 3.5E8,
    "description": "Hermosa casa familiar en zona residencial.",
    "name": "Casa Los Nogales",
    "rooms": 4,
    "bathrooms": 3,
    "parkingLots": 2,
    "areaSquareMeters": 120.5,
    "owner": "juan.perez@example.com"
}
````

- `POST api/v1/properties`: Create a new property

Example input dto:
```json
{
  "address": "Cra 15 # 123-45",
  "city": "Bogotá",
  "active": true,
  "price": 350000000,
  "description": "Hermosa casa familiar en zona residencial.",
  "name": "Casa Los Nogales",
  "rooms": 4,
  "bathrooms": 3,
  "parkingLots": 2,
  "areaSquareMeters": 120.5,
  "realStateName": "Inmobiliaria MiCasa",
  "type": "HOUSE",
  "owner": "juan.perez@example.com"
}
```

- `PUT api/v1/properties/{id}`: Update an existing property with the body similar to the `POST` request
- `DELETE api/v1/properties/{id}`: Delete a property by ID

## Deployment



## Video demonstration

[Video](https://www.youtube.com/)