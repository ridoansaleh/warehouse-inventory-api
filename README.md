# Warehouse Inventory API

This project provides a RESTful API for managing a smartphone warehouse system.
It supports creating and managing items, variants, and stock levels, as well as recording sales transactions while ensuring stock accuracy.

## Tech Stack

- Spring Boot 3.5.6
- Spring Data JPA
- H2 In-memory database
- Java 25
- Maven


## Setup

### 1. Clone

```bash
git clone https://github.com/ridoansaleh/warehouse-inventory-api.git
cd warehouse-inventory-api
```

### 2. Build

```bash
./mvnw clean install
```

### 3. Run

```bash
./mvnw spring-boot:run
```

## Database

The project uses an H2 in-memory database by default.
H2 Console is available at: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

In this project, there are two SQL files used for automatic database setup:

#### `schema.sql`
- Defines the **database structure** — including tables, columns, and relationships.  
- When the application starts, Spring Boot automatically executes this file to **create all required tables** in the H2 database.  
- It ensures the database schema is always consistent with the project’s data model.

#### `data.sql`
- Contains **initial or sample data** that populates the database after the schema is created.  
- Useful for seeding data such as default smartphones, variants, or stock levels.  
- This allows you to test the API immediately without manually inserting records.

#### H2 Connection Settings:

```bash
JDBC URL: jdbc:h2:mem:warehouse_inventory
Username: sa
Password: <leave empty>
```

## Base URL

```http://localhost:8080/api```

## Endpoints Overview

| Resource | Method | Endpoint | Description |
|-----------|---------|-----------|--------------|
| Items | `GET` | [`/items`](#1-get-all-items) | Get all smartphone items |
|  | `GET` | [`/items/{id}`](#2-get-an-item) | Get a specific item by ID |
|  | `POST` | [`/items`](#3-create-a-new-item) | Create a new item |
| Variants | `GET` | [`/variants`](#4-get-all-variants) | Get all variants |
|  | `GET` | [`/variants/{id}`](#5-get-a-variant) | Get a specific variant by ID |
|  | `POST` | [`/variants`](#6-create-a-new-variant) | Create a new variant |
| Stock Levels | `GET` | [`/stock-levels`](#7-get-all-stock-levels) | Get all stock levels |
|  | `POST` | [`/stock-levels`](#8-create-a-stock-level) | Add new stock record |
|  | `PUT` | [`/stock-levels/{id}`](#9-update-a-stock-level-quantity) | Update stock quantity |
| Sales | `POST` | [`/sales`](#10-create-a-new-sale) | Create a sale record and update stock quantity |

---

## Example Requests

### 1. Get all Items
**Request**
```http
GET /api/items
```

**Response**

```json
[
  {
    "id": 1,
    "name": "iPhone 16",
    "brand": "Apple",
    "description": "Apple smartphone with A18 chip"
  },
  {
    "id": 2,
    "name": "Galaxy S25",
    "brand": "Samsung",
    "description": "Samsung flagship phone with advanced camera"
  },
  {
    "id": 3,
    "name": "Google Pixel 10",
    "brand": "Google",
    "description": "Flagship Pixel with advanced AI camera"
  }
]
```

### 2. Get an Item
**Request**
```http
GET /api/items/1
```

**Response**

```json
{
  "id": 1,
  "name": "iPhone 16",
  "brand": "Apple",
  "description": "Apple smartphone with A18 chip"
}
```


### 3. Create a New Item
**Request**
```http
POST /api/items
Content-Type: application/json
```

**Body**

```json
{
  "name": "Pixel 9",
  "brand": "Google",
  "description": "Google smartphone with Tensor G4"
}
```

**Response**

```json
{
  "id": 4,
  "name": "Pixel 9",
  "brand": "Google",
  "description": "Google smartphone with Tensor G4"
}
```


### 4. Get all Variants
**Request**
```http
GET /api/variants
```

**Response**

```json
[
  {
    "id": 1,
    "itemId": 1,
    "name": "iPhone 16 - 128GB Black",
    "sku": "IPH16-BLK-128",
    "attributes": "{\"color\": \"Black\", \"storage\": \"128GB\"}",
    "price": 999.00
  },
]
```


### 5. Get a Variant
**Request**
```http
GET /api/variants/2
```

**Response**

```json
{
  "id": 1,
  "itemId": 1,
  "name": "iPhone 16 - 256GB Silver",
  "sku": "IPH16-256-SLV",
  "attributes": "{\"color\": \"Silver\", \"storage\": \"256GB\"}",
  "price": 1099.00
}
```

### 6. Create a new Variant
**Request**
```http
GET /api/variants
```

**Body**

```json
{
  "name": "New iPhone XYZ Red 128GB",
  "sku": "NP-RED-128",
  "attributes": {
      "color": "Red",
      "storage": "128GB"
  },
  "price": 89.00,
  "itemId": 1 
}
```

**Response**

```json
{
  "id": 7,
  "name": "New iPhone XYZ Red 128GB",
  "sku": "NP-RED-128",
  "attributes": "{\"color\":\"Red\",\"storage\":\"128GB\"}",
  "price": 89.00,
  "item": {
      "id": 1,
      "name": "iPhone 16",
      "brand": "Apple",
      "description": "Latest Apple flagship smartphone with A18 chip",
      "createdAt": "2025-10-24T08:59:30.205322",
      "updatedAt": "2025-10-24T08:59:30.205322"
  }
}
```


### 7. Get all Stock levels
**Request**
```http
GET /api/stock-levels
```

**Response**

```json
[
  {
    "id": 1,
    "quantity": 25,
    "variant": {
        "id": 1,
        "name": "iPhone 16 - 128GB Black",
        "sku": "IPH16-128-BLK",
        "attributes": "\"{\\\"color\\\": \\\"black\\\", \\\"storage\\\": \\\"128GB\\\"}\"",
        "price": 999.00,
        "item": {
            "id": 1,
            "name": "iPhone 16",
            "brand": "Apple",
            "description": "Latest Apple flagship smartphone with A18 chip",
            "createdAt": "2025-10-24T08:59:30.205322",
            "updatedAt": "2025-10-24T08:59:30.205322"
        }
    }
  },
  {
    "id": 2,
    "quantity": 10,
    "variant": {
        "id": 2,
        "name": "iPhone 16 - 256GB Silver",
        "sku": "IPH16-256-SLV",
        "attributes": "\"{\\\"color\\\": \\\"silver\\\", \\\"storage\\\": \\\"256GB\\\"}\"",
        "price": 1099.00,
        "item": {
            "id": 1,
            "name": "iPhone 16",
            "brand": "Apple",
            "description": "Latest Apple flagship smartphone with A18 chip",
            "createdAt": "2025-10-24T08:59:30.205322",
            "updatedAt": "2025-10-24T08:59:30.205322"
        }
    }
  }
]
```


### 8. Create a Stock level
**Request**
```http
POST /api/stock-levels
Content-Type: application/json
```

**Body**

```json
{
  "variantId": 1,
  "quantity": 7
}
```

**Response**

```json
{
  "variantId": 1,
  "newQuantity": 7,
  "message": "Stock updated successfully"
}
```


### 9. Update a Stock level Quantity
**Request**
```http
PUT /api/stock-levels/1
Content-Type: application/json
```

**Body**

```json
{
  "quantity": 45
}
```

**Response**

```json
{
  "id": 1,
  "variantId": 1,
  "quantity": 45
}
```

### 10. Create a new Sale
**Request**
```http
POST /api/sales
Content-Type: application/json
```

**Body**

```json
{
  "variantId": 1,
  "quantity": 2
}
```

**Response**

```json
{
  "message": "Sale recorded successfully.",
  "variantId": 1,
  "quantitySold": 2,
  "remainingStock": 13
}
```