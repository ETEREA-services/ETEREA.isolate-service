# Eterea Isolate Service

## Overview
Eterea Isolate Service is a microservice built with Spring Boot that provides isolation and data management functionality for the Eterea system. It's designed to handle various business operations including invoice management, client movements, and article management.

## Features
- Invoice number sequence validation and completion
- Client movement tracking
- Article and stock management
- Integration with core services via Feign clients
- Eureka service discovery integration
- OpenAPI documentation

## Technical Stack
- Java 21
- Spring Boot 3.4.4
- Spring Cloud 2024.0.1
- Spring Cloud Netflix Eureka Client
- Spring Cloud OpenFeign
- Lombok
- Caffeine Cache
- SpringDoc OpenAPI

## Project Structure
```
src/main/java/eterea/isolate/service/
├── client/
│   └── core/
│       └── ClienteMovimientoClient.java
├── configuration/
│   └── IsolateServiceConfiguration.java
├── controller/
│   └── CompletarFaltantesController.java
├── model/
│   └── dto/
│       ├── ArticuloDto.java
│       ├── ArticuloMovimientoDto.java
│       ├── ClienteDto.java
│       ├── ClienteMovimientoDto.java
│       ├── ComprobanteDto.java
│       ├── CuentaDto.java
│       └── MonedaDto.java
├── service/
│   ├── CompletarFaltantesService.java
│   └── miscelaneos/
│       └── ToolService.java
└── IsolateServiceApplication.java
```

## Configuration
The service is configured through `bootstrap.yml` with the following key properties:
- Server port: Configurable via `APP_PORT` environment variable (default: 8080)
- Eureka client configuration
- Logging levels
- Feign client timeouts

## API Endpoints
### Completar Faltantes
- **Endpoint**: `/api/isolate/completarFaltantes/complete/{letraComprobante}/{puntoVenta}/{numeroComprobanteDesde}/{numeroComprobanteHasta}`
- **Method**: GET
- **Description**: Validates and completes missing invoice numbers in a given range
- **Parameters**:
  - `letraComprobante`: Invoice letter type
  - `puntoVenta`: Sales point ID
  - `numeroComprobanteDesde`: Starting invoice number
  - `numeroComprobanteHasta`: Ending invoice number

## Building and Running

### Prerequisites
- JDK 21
- Maven 3.9.9 or later
- Docker (optional, for containerized deployment)

### Local Development
1. Clone the repository
2. Build the project:
   ```bash
   ./mvnw clean package -DskipTests
   ```
3. Run the application:
   ```bash
   java -jar target/eterea-isolate-service-0.0.1-SNAPSHOT.jar
   ```

### Docker Deployment
Build and run using Docker:
```bash
docker build -f Dockerfile.local -t eterea-isolate-service .
docker run -p 8080:8080 eterea-isolate-service
```

## Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Validation
- Spring Cloud Starter
- Spring Cloud Netflix Eureka Client
- Spring Cloud OpenFeign
- Spring Boot Starter Actuator
- Spring Boot Starter HATEOAS
- Caffeine Cache
- SpringDoc OpenAPI
- Lombok

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

