# Warehouse Inventory API

This document describes the REST API endpoints for managing the warehouse inventory of smartphones, including items, variants, and stock levels.

## Tech Stack

- Spring Boot 3.5.6
- Spring Data JPA
- H2 In-memory database
- Java 25
- Maven


## Setup & Run

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
| Items | `GET` | `/items` | Get all smartphone items |
|  | `GET` | `/items/{id}` | Get a specific item by ID |
|  | `POST` | `/items` | Create a new item |
| Variants | `GET` | `/variants` | Get all variants |
|  | `GET` | `/variants/{id}` | Get a specific variant by ID |
|  | `POST` | `/variants` | Create a new variant |
| Stock Levels | `GET` | `/stock-levels` | Get all stock levels |
|  | `POST` | `/stock-levels` | Add new stock record |
|  | `PUT` | `/stock-levels/{id}` | Update stock quantity |
| Sales | `POST` | `/sales` | Create a sale request and update stock quantity |

---

## Example Requests

### 1. Get All Items
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


### 2. Create a New Item
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
  "id": 3,
  "name": "Pixel 9",
  "brand": "Google",
  "description": "Google smartphone with Tensor G4"
}
```

### 3. Get All Variants
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
    "name": "iPhone 16 Black 128GB",
    "sku": "IPH16-BLK-128",
    "attributes": "{\"color\": \"Black\", \"storage\": \"128GB\"}",
    "price": 1099.00
  },
  // other variants  
]
```


### 4. Update Stock Quantity
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

### 5. Create a new Sales
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