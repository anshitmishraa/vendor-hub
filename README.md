# VendorHub

VendorHub is a Spring Boot-based service that handles CRUD (Create, Read, Update, Delete) operations for managing vendor information. This system provides validation mechanisms for vendor attributes, ensuring data integrity and consistency. It includes functionality for paginated retrieval, as well as UI interaction for managing vendors through a web interface.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Validation](#validation)
- [UI Interaction](#ui-interaction)
- [Pagination](#pagination)
- [Error Handling](#error-handling)

## Features

1. **Vendor Management**: CRUD operations for vendor entities, including:
  - Creating a new vendor
  - Retrieving a vendor by ID
  - Retrieving all vendors with pagination
  - Updating vendor details
  - Deleting a vendor

2. **Validation**: Ensures that vendor details such as bank account number, bank name, and address follow business rules.

3. **Error Handling**: Graceful error handling with appropriate error messages when vendor operations fail.

4. **Pagination**: Supports pagination for vendor retrieval to manage large datasets efficiently.

5. **Web Interface**: Provides a web-based interface to manage vendor information via forms and tables.

## Technologies

- **Java 17**
- **Spring Boot 3.x**
- **JPA (Hibernate)**
- **Jakarta Persistence API**
- **PostgreSQL (or any RDBMS)**

## Project Structure

```bash
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.project.VendorHub
│   │   │       ├── entity
│   │   │       ├── repository
│   │   │       ├── service
│   │   │       └── validation
│   │   ├── resources
│   │   │   └── application.properties
│   └── test
└── pom.xml
```

### Key Directories

- **entity**: Contains the `Vendor` entity class representing the vendor table in the database.
- **repository**: JPA repository for performing database operations.
- **service**: Contains `VendorService`, which handles business logic.
- **validation**: Vendor validation rules are defined here.

## Installation

### Prerequisites

- JDK 17 or later
- Maven 3.x
- PostgreSQL (or any RDBMS of your choice)

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/anshitmishraa/vendor-hub.git
   cd vendor-hub
   ```

2. Configure the database settings in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/vendorhub
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Build the project:

   ```bash
   mvn clean install
   ```

4. Run the application:

   ```bash
   mvn spring-boot:run
   ```

5. Access the application at `http://localhost:8080`.

## Usage

### API Documentation

| Endpoint              | Method | Description               | Parameters                         |
|-----------------------|--------|---------------------------|-------------------------------------|
| `/vendors`            | POST   | Create a new vendor        | `Vendor` JSON                      |
| `/vendors`            | GET    | Get all vendors (paginated)| `page`, `size`                     |
| `/vendors/{id}`       | GET    | Get a vendor by ID         | `id` (Path Variable)               |
| `/vendors/{id}`       | PUT    | Update a vendor by ID      | `id` (Path Variable), `Vendor` JSON|
| `/vendors/{id}`       | DELETE | Delete a vendor by ID      | `id` (Path Variable)               |

#### Sample JSON for Vendor

```json
{
  "name": "Vendor Name",
  "bankAccountNo": "1234567890",
  "bankName": "Bank Name",
  "addressLine1": "123 Street",
  "addressLine2": "Suite 100",
  "city": "City",
  "country": "Country",
  "zipCode": "12345"
}
```

### Pagination

Use the following query parameters for paginated results:

- **page**: The page number (starting from 0).
- **size**: The number of vendors per page.

Example:

```http
GET /vendors?page=0&size=10
```

### VendorService Methods

- **`createVendor(Vendor vendor)`**: Creates a new vendor after validation.
- **`getAllVendors(Pageable pageable)`**: Retrieves all vendors with pagination.
- **`getVendorById(Long id)`**: Fetches a vendor by its ID.
- **`updateVendor(Long id, Vendor vendorDetails)`**: Updates the details of an existing vendor.
- **`deleteVendor(Long id)`**: Deletes a vendor by its ID.

### Validation

The `VendorValidation` class provides validation for the following fields:

- **Name**: Cannot be empty.
- **Bank Account Number**: Must be numeric and cannot exceed a defined maximum length.
- **Bank Name**: Cannot be empty.
- **Zip Code**: Must be numeric and cannot exceed a defined maximum length.
- **Address Line 1 and 2**: Validated for length.
- **City**: Validated for length.
- **Country**: Validated for length.

Validation constants are defined in `Constants`.

### Error Handling

The service throws relevant exceptions with detailed error messages for invalid operations, such as:

- **EntityNotFoundException**: When a vendor is not found by the provided ID.
- **IllegalArgumentException**: When validation rules fail.

### UI Interaction

The frontend interacts with the backend API using `fetch` for the following operations:

1. **Create Vendor**: Submits a new vendor form.
2. **Edit Vendor**: Populates form fields with existing vendor details for editing.
3. **Delete Vendor**: Sends a delete request for a vendor by its ID.
4. **Pagination**: Displays previous and next buttons for paginated results.

### Example UI Interactions

1. **Create a Vendor**:

   A form is used to input vendor details. Upon form submission, the details are sent via a POST request to the backend.

2. **Edit a Vendor**:

   The system fetches the vendor data based on the selected ID, populates the edit form, and updates the details via a PUT request.

3. **Delete a Vendor**:

   When a delete action is triggered, a DELETE request is sent to the backend to remove the vendor.

4. **Pagination**:

   Pagination controls allow navigation between pages of vendor results.

## Error Handling

The UI provides feedback through:

- **Error Messages**: Displayed in case of failed operations such as validation errors or server errors.
- **Success Messages**: Shown upon successful vendor creation, update, or deletion.

Example error:

```json
{
  "timestamp": "2024-09-16T10:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Vendor not found"
}
```

## Conclusion

VendorHub provides an efficient way to manage vendor data with proper validation, error handling, and a responsive UI interface. This service is built with scalability in mind, making use of pagination for large datasets.