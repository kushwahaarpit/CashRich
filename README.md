# CashRich



## Overview

CashRich is a backend application built with Spring Boot that allows users to create and update profiles, and retrieve cryptocurrency data from a third-party API (CoinMarketCap). This application includes user signup, login, and profile update functionalities with secure API access and proper validation.

## Tech Stack

- **Spring Boot**
- **Hibernate (JPA)**
- **MySQL**
- **Spring Security**
- **RestTemplate** for API calls
- **Lombok** (optional)

## Features

- **User Signup**: Users can sign up with a unique username, email, and a secure password.
- **User Login**: Authenticate users and allow them to log in.
- **Profile Management**: Users can update their profile information (First Name, Last Name, Mobile, Password).
- **Coin Data Retrieval**: Retrieve cryptocurrency data from CoinMarketCap using a secure API call.





## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/kushwahaarpit/CashRichAssesment.git
   cd mycashrichapp

   

CREATE DATABASE my_cashRich_db;

Update your application.properties with your MySQL credentials:

properties

spring.datasource.url=jdbc:mysql://localhost:3306/my_cashRich_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=your-username
spring.datasource.password=your-password

Build the project:

bash

./mvnw clean install

Run the application:

bash

    ./mvnw spring-boot:run

Usage

You can test the application endpoints using tools like Postman or curl.
1. User Signup

   Endpoint: POST /api/users/signup

   Description: This endpoint allows new users to sign up by providing their details.

   Request Body:

   json

{
"firstName": "John",
"lastName": "Doe",
"email": "john.doe@example.com",
"mobile": "1234567890",
"username": "johndoe",
"password": "Secure@123"
}

Expected Response:

json

    {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "mobile": "1234567890",
      "username": "johndoe"
    }

2. User Login

   Endpoint: POST /api/users/login

   Description: This endpoint allows users to log in by providing their username and password.

   Request Body:

   json

{
"username": "johndoe",
"password": "Secure@123"
}

Expected Response: (JWT or session details, depending on your implementation)

json

    {
      "message": "Login successful",
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }

3. Update Profile

   Endpoint: PUT /api/users/{userId}/update

   Description: This endpoint allows users to update their profile information.

   Request Body:

   json

{
"firstName": "Johnny",
"lastName": "Doe",
"mobile": "0987654321",
"password": "NewSecure@123"
}

Expected Response:

json

    {
      "id": 1,
      "firstName": "Johnny",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "mobile": "0987654321",
      "username": "johndoe"
    }

4. Get Coin Data

   Endpoint: GET /api/coins/data

   Description: This endpoint allows logged-in users to retrieve cryptocurrency data from the CoinMarketCap API.

   Request Parameters:
   symbols (required): Comma-separated list of cryptocurrency symbols (e.g., BTC,ETH,LTC).

   Example Request:

   bash

GET /api/coins/data?symbols=BTC,ETH,LTC

Expected Response:

json

    {
      "BTC": {
        "price": 45000.00,
        "market_cap": 850000000000,
        "volume_24h": 35000000000,
        "percent_change_24h": 2.5
      },
      "ETH": {
        "price": 3000.00,
        "market_cap": 350000000000,
        "volume_24h": 15000000000,
        "percent_change_24h": 1.8
      },
      "LTC": {
        "price": 180.00,
        "market_cap": 12000000000,
        "volume_24h": 3000000000,
        "percent_change_24h": 0.5
      }
    }

5. Error Handling

   Scenario: Invalid user signup due to non-unique username

   Expected Response:

   json

{
"error": "Username already exists"
}

Scenario: Invalid login attempt

Expected Response:

json

    {
      "error": "Invalid username or password"
    }


Running Tests

To run the tests:

bash

./mvnw test

Security

    Passwords are securely stored using BCryptPasswordEncoder.
    API endpoints are protected using Spring Security, allowing only authenticated users to access certain endpoints.

API Integration

This application integrates with the CoinMarketCap API to fetch cryptocurrency data. Make sure to set up your API key in the application.properties file:

properties

coinmarketcap.api.key=your-api-key
coinmarketcap.api.url=https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest

