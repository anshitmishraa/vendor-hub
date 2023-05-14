# Vendor Hub

## Table of Contents

- [Introduction](#introduction)
- [API Documentation](#api-documentation)

## Introduction

This project aims to develop a web application using Java Spring Boot and a chosen database. The application will have the following features:
- Create Vendor: Users can enter details such as Vendor Name, Bank Account No., Bank Name, Address Line 1, Address Line 2, City, Country, and Zip Code to create a new vendor. The fields marked with (*) are mandatory. 
- Display Paginated List of Vendors: The application will display a paginated list of vendors, showing their Vendor Name, Bank Account No., Bank Name, and options to edit or delete each vendor. 
- Edit Vendor: Users can edit the details of a vendor by loading the vendor's information on the edit vendor screen. After making the necessary changes, they can submit the form to update the vendor's details. 
- Delete Vendor: Users will have the option to delete a vendor after confirming their action. Once deleted, the vendor will be permanently removed from the system.

The web application will provide a user-friendly interface for managing vendor information, ensuring the mandatory fields are filled, and offering convenient options for editing and deleting vendors.

## API Documentation

This document provides detailed information about the API endpoints available in the VendorHub application.

### Base URL
The base URL for all API endpoints is: `/vendors`

#### Create a Vendor

##### Endpoint: `POST /vendors`

Creates a new vendor with the provided vendor details.

##### Request Body:
```json
{
  "name": "Deepak Jayaprakash",
  "bankAccountNo": "6543214859",
  "bankName": "DEF Bank",
  "addressLine1": "686 TYPE 2 SECTOR 1",
  "addressLine2": "BHEL RANIPUR",
  "city": "HARIDWAR",
  "country": "India",
  "zipCode": "249403"
}
```

##### Response:

Success: `201 Created`

```json
{
"message": "Vendor has been created successfully."
}
```

Failure: `400 Bad Request`

```json
{
"message": "Error message describing the issue."
}
```

#### Get All Vendors

##### Endpoint: `GET /vendors`

Retrieves a paginated list of all vendors.

###### Parameters:

- `page (optional)`: Page number to retrieve `(default: 1)`
- `size (optional)`: Number of vendors per page `(default: 10)`
  
##### Response:

Success: `200 OK`

```json
{
  "content": [
    {
      "id": 1,
      "name": "Deepak Jayaprakash",
      "bankAccountNo": "6543214859",
      "bankName": "DEF Bank",
      "addressLine1": "686 TYPE 2 SECTOR 1",
      "addressLine2": "BHEL RANIPUR",
      "city": "HARIDWAR",
      "country": "India",
      "zipCode": "249403"
    }
  ],
  "pageable": {
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 14,
    "unpaged": false,
    "paged": true
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 1,
  "size": 14,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "first": true,
  "numberOfElements": 1,
  "empty": false
}
```

Failure: `500 Internal Server Error`

#### Get Vendor by ID

##### Endpoint: `GET /vendors/{id}`

Retrieves the details of a vendor with the specified ID.

###### Path Parameter:

- `id`: The ID of the vendor to retrieve.

###### Response:

Success: `200 OK`

```json
{
  "id": 1,
  "name": "Deepak Jayaprakash",
  "bankAccountNo": "6543214859",
  "bankName": "DEF Bank",
  "addressLine1": "686 TYPE 2 SECTOR 1",
  "addressLine2": "BHEL RANIPUR",
  "city": "HARIDWAR",
  "country": "India",
  "zipCode": "249403"
}
```

Failure:
- `Vendor not found: 404 Not Found`
    ```json
    
    {
        "message": "Vendor not found."
    }
    ```

- Server error: `500 Internal Server Error`

#### Update Vendor
##### Endpoint: `PUT /vendors/{id}`

Updates the details of a vendor with the specified ID.

###### Path Parameter:

- `id`: The ID of the vendor to update.

###### Request Body:

```json
{
  "id": 1,
  "name": "Deepak Jayaprakash",
  "bankAccountNo": "6543214859",
  "bankName": "DEF Bank",
  "addressLine1": "686 TYPE 2 SECTOR 1",
  "addressLine2": "BHEL RANIPUR",
  "city": "HARIDWAR",
  "country": "India",
  "zipCode": "249403"
}
```

###### Response:

Success: `200 OK`

```json
{
    "message": "Vendor has been updated successfully."
}
```

Failure:

`Vendor not found: 404 Not Found`

```json
{
    "message": "Vendor not found."
}
```

Bad request: `400 Bad Request`

```json
{
    "message": "Error message describing the issue."
}
```

Server error: `500 Internal Server Error`

#### Delete Vendor

##### Endpoint: `DELETE /vendors/{id}`

Deletes a vendor with the specified ID.

###### Path Parameter:

- `id`: The ID of the vendor to delete.
Response:

Success: `202 Accepted`

```json
{
  "message": "Vendor has been deleted successfully."
}
```

Failure:

`Vendor not found: 404 Not Found`

```json
{
  "message": "Vendor not found."
}
```

Server error: `500 Internal Server Error`

### Error Responses
The API endpoints may return the following error responses:

- `400 Bad Request`: The request is invalid or missing required fields.
- `404 Not Found`: The requested resource (vendor or endpoint) was not found.
- `500 Internal Server Error`: An internal server error occurred.