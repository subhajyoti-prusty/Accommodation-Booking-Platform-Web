# 🏨 Accommodation Booking Platform - Backend

A comprehensive Spring Boot-based accommodation booking system designed for hotels, resorts, and other hospitality businesses. This backend API provides complete functionality for user management, room booking, payment processing, and notification services.

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation & Setup](#-installation--setup)
- [Configuration](#-configuration)
- [Database Schema](#-database-schema)
- [API Documentation](#-api-documentation)
- [Security](#-security)
- [Payment Integration](#-payment-integration)
- [Contributing](#-contributing)
- [License](#-license)

## ✨ Features

### User Management
- **User Registration & Authentication** - Secure JWT-based authentication
- **Role-based Access Control** - Admin and Customer roles
- **User Profile Management** - Complete user data management

### Room Management
- **Room Types** - Single, Double, Suite, Triple accommodations
- **Room Availability** - Real-time availability checking
- **Dynamic Pricing** - Flexible pricing per room type
- **Room Details** - Comprehensive room information with images

### Booking System
- **Real-time Booking** - Instant booking confirmation
- **Booking Management** - Complete booking lifecycle management
- **Booking Status Tracking** - Track booking states (Pending, Confirmed, Cancelled)
- **Date Range Validation** - Check-in/check-out date validation

### Payment Processing
- **Stripe Integration** - Secure payment processing
- **Payment Status Tracking** - Real-time payment status updates
- **Multiple Payment States** - Pending, Completed, Failed, Refunded

### Notification System
- **Email Notifications** - Automated booking confirmations and updates
- **Multi-type Notifications** - Support for various notification types
- **Notification History** - Complete notification tracking

## 🛠 Tech Stack

### Core Technologies
- **Java 21** - Latest LTS version
- **Spring Boot 3.5.5** - Modern Spring framework
- **Spring Security** - Authentication & authorization
- **Spring Data JPA** - Data persistence layer
- **Hibernate** - ORM framework

### Database
- **MySQL 8** - Primary database
- **JPA/Hibernate** - Database abstraction layer

### Security & Authentication
- **JWT (JSON Web Tokens)** - Stateless authentication
- **BCrypt** - Password hashing
- **CORS Configuration** - Cross-origin resource sharing

### Payment & External Services
- **Stripe API** - Payment processing
- **Spring Mail** - Email service integration

### Development Tools
- **Maven** - Dependency management & build tool
- **Lombok** - Code generation
- **ModelMapper** - Object mapping
- **Spring Boot DevTools** - Development utilities

## 📁 Project Structure

```
AccommodationBookingBE/
├── src/
│   ├── main/
│   │   ├── java/com/subhajyoti/AccommodationBookingBE/
│   │   │   ├── AccommodationBookingBeApplication.java
│   │   │   ├── config/           # Configuration classes
│   │   │   ├── controllers/      # REST API controllers
│   │   │   ├── dtos/            # Data Transfer Objects
│   │   │   ├── entities/        # JPA entities
│   │   │   ├── enums/           # Enumeration classes
│   │   │   ├── exceptions/      # Custom exceptions
│   │   │   ├── notification/    # Notification services
│   │   │   ├── payments/        # Payment processing
│   │   │   ├── repositories/    # Data access layer
│   │   │   ├── security/        # Security configuration
│   │   │   └── services/        # Business logic layer
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/          # Static resources
│   │       └── templates/       # Email templates
│   └── test/                    # Test classes
├── target/                      # Compiled classes
├── pom.xml                      # Maven configuration
└── README.md                    # This file
```

## 📋 Prerequisites

Before running this application, ensure you have:

- **Java 21** or higher installed
- **Maven 3.6+** for dependency management
- **MySQL 8.0+** database server
- **Git** for version control
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code recommended)

## 🚀 Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/subhajyoti-prusty/Accommodation-Booking-Platfrom-Web.git
cd Accommodation-Booking-Platfrom-Web/AccommodationBookingBE
```

### 2. Database Setup
```sql
-- Create database
CREATE DATABASE accommodation_booking_db;

-- Create user (optional)
CREATE USER 'booking_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON accommodation_booking_db.* TO 'booking_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configure Application Properties
Update `src/main/resources/application.properties`:

```properties
# Application Configuration
spring.application.name=AccommodationBookingBE
server.port=8081

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/accommodation_booking_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
spring.jpa.format-sql=true

# JWT Configuration
secreteJwtString=your_secure_jwt_secret_key_here

# Email Configuration (Optional)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Stripe Configuration (Optional)
stripe.api.key=your_stripe_secret_key
```

### 4. Install Dependencies & Run
```bash
# Install dependencies
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8081`

## ⚙️ Configuration

### Environment Variables
For production deployment, consider using environment variables:

```bash
export DB_URL=jdbc:mysql://localhost:3306/accommodation_booking_db
export DB_USERNAME=your_username
export DB_PASSWORD=your_password
export JWT_SECRET=your_jwt_secret
export STRIPE_SECRET_KEY=your_stripe_key
```

### Profiles
The application supports different profiles:

- `dev` - Development environment
- `prod` - Production environment
- `test` - Testing environment

Run with specific profile:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## 🗄️ Database Schema

### Core Entities

#### Users Table
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(20) NOT NULL,
    role ENUM('ADMIN', 'CUSTOMER') NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    created_at DATE NOT NULL
);
```

#### Rooms Table
```sql
CREATE TABLE room (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(50) UNIQUE NOT NULL,
    type ENUM('SINGLE', 'DOUBLE', 'SUIT', 'TRIPLE') NOT NULL,
    price_per_night DECIMAL(10,2) NOT NULL,
    capacity INT NOT NULL,
    description TEXT,
    image_url VARCHAR(500)
);
```

#### Bookings Table
```sql
CREATE TABLE booking (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    payment_status ENUM('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED'),
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    booking_reference VARCHAR(100) UNIQUE,
    created_at DATE NOT NULL,
    booking_status ENUM('PENDING', 'CONFIRMED', 'CANCELLED', 'COMPLETED'),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (room_id) REFERENCES room(id)
);
```

## 📚 API Documentation

### Authentication Endpoints
```
POST /auth/register        # User registration
POST /auth/login          # User login
POST /auth/refresh        # Refresh JWT token
POST /auth/logout         # User logout
```

### User Management
```
GET    /users/profile     # Get user profile
PUT    /users/profile     # Update user profile
GET    /admin/users       # Get all users (Admin only)
DELETE /admin/users/{id}  # Delete user (Admin only)
```

### Room Management
```
GET    /rooms             # Get all available rooms
GET    /rooms/{id}        # Get room details
POST   /admin/rooms       # Create room (Admin only)
PUT    /admin/rooms/{id}  # Update room (Admin only)
DELETE /admin/rooms/{id}  # Delete room (Admin only)
GET    /rooms/search      # Search rooms by criteria
```

### Booking Management
```
POST   /bookings          # Create new booking
GET    /bookings          # Get user bookings
GET    /bookings/{id}     # Get booking details
PUT    /bookings/{id}     # Update booking
DELETE /bookings/{id}     # Cancel booking
GET    /admin/bookings    # Get all bookings (Admin only)
```

### Payment Processing
```
POST   /payments/create   # Create payment intent
POST   /payments/confirm  # Confirm payment
GET    /payments/{id}     # Get payment status
POST   /payments/refund   # Process refund (Admin only)
```

### Sample API Requests

#### User Registration
```json
POST /auth/register
{
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "password": "securePassword123",
    "phoneNumber": "+1234567890",
    "role": "CUSTOMER"
}
```

#### Create Booking
```json
POST /bookings
{
    "roomId": 1,
    "checkInDate": "2024-12-01",
    "checkOutDate": "2024-12-05",
    "guestCount": 2,
    "specialRequests": "Late check-in requested"
}
```

## 🔐 Security

### Authentication Flow
1. User registers/logs in with credentials
2. Server validates credentials and generates JWT
3. Client includes JWT in Authorization header for subsequent requests
4. Server validates JWT for protected endpoints

### Security Features
- **Password Encryption** - BCrypt hashing
- **JWT Token Security** - Secure token generation and validation
- **Role-based Access Control** - Admin and Customer roles
- **CORS Configuration** - Cross-origin request handling
- **Input Validation** - Request data validation
- **SQL Injection Prevention** - JPA/Hibernate protection

### JWT Token Structure
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

## 💳 Payment Integration

### Stripe Integration
The application integrates with Stripe for secure payment processing:

- **Payment Intents** - Secure payment processing
- **Webhook Support** - Real-time payment status updates
- **Refund Processing** - Automated refund handling
- **Payment History** - Complete transaction tracking

### Payment Flow
1. User initiates booking
2. System calculates total amount
3. Payment intent created with Stripe
4. User completes payment
5. Webhook confirms payment
6. Booking status updated

## 🧪 Testing

### Run Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=UserServiceTest

# Run with coverage
mvn test jacoco:report
```

### Test Categories
- **Unit Tests** - Service layer testing
- **Integration Tests** - API endpoint testing
- **Repository Tests** - Data access testing
- **Security Tests** - Authentication & authorization testing

## 🚀 Deployment

### Docker Deployment
```dockerfile
FROM openjdk:21-jdk-slim
COPY target/AccommodationBookingBE-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]
```

### Production Considerations
- Use environment variables for sensitive configuration
- Set up proper logging configuration
- Configure database connection pooling
- Set up monitoring and health checks
- Use HTTPS in production
- Configure proper CORS settings

## 🤝 Contributing

1. **Fork the repository**
2. **Create feature branch** (`git checkout -b feature/AmazingFeature`)
3. **Commit changes** (`git commit -m 'Add some AmazingFeature'`)
4. **Push to branch** (`git push origin feature/AmazingFeature`)
5. **Open Pull Request**

### Development Guidelines
- Follow Java coding conventions
- Write comprehensive tests
- Update documentation for new features
- Use meaningful commit messages
- Ensure all tests pass before submitting PR

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Subhajyoti Prusty**
- GitHub: [@subhajyoti-prusty](https://github.com/subhajyoti-prusty)
- Email: subhajyoti.2004@gmail.com

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- MySQL team for the robust database system
- Stripe for secure payment processing
- All contributors who helped improve this project

---

## 📞 Support

If you encounter any issues or have questions:

1. Check the [Issues](https://github.com/subhajyoti-prusty/Accommodation-Booking-Platfrom-Web/issues) page
2. Create a new issue with detailed description
3. Join our community discussions
4. Contact the maintainer directly

---

**Happy Coding! 🚀**