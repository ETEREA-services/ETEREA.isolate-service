# Eterea Isolate Service

![Java](https://img.shields.io/badge/java-24-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.0.0-yellow.svg)
[![Maven Central](https://img.shields.io/maven-central/v/com.termascacheuta/eterea-isolate-service.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.termascacheuta%22%20AND%20a:%22eterea-isolate-service%22)

## Novedades en la versión 0.5.0

- **Soporte experimental para despliegue nativo:** Ahora es posible construir una imagen nativa usando GraalVM, lo que permite tiempos de arranque más rápidos y menor consumo de memoria. Consulta el `Dockerfile.graalvm` para más detalles.

## Overview
Eterea Isolate Service is a lightweight, standalone Spring Boot microservice designed to run occasional or specific processes on the Eterea ecosystem without requiring a full redeployment of the main services.

This approach allows for greater flexibility and agility when performing maintenance tasks, data migrations, or executing batch processes that are not part of the core, real-time business logic.

## Features
- **Process Isolation**: Run tasks in a separate environment, minimizing impact on the core production services.
- **Flexibility**: Easily add new controllers and services for specific, one-off tasks.
- **Invoice Management**: Includes functionality to validate and complete missing invoice number sequences.
- **Integration**: Connects to core services via Feign clients to access necessary data and functionality.
- **Service Discovery**: Integrates with Consul for seamless communication within the microservices architecture.
- **API Documentation**: Provides clear API documentation through OpenAPI.

## Technical Stack
...existing code...
- Java 24
- Spring Boot 3.5.3
- Spring Cloud 2025.0.0
- Spring Cloud Consul Discovery
- Spring Cloud OpenFeign
- Lombok
- Caffeine Cache
- SpringDoc OpenAPI
- **GraalVM Native Image (experimental)**

## Project Structure
```
src/main/java/eterea/isolate/service/
├── adapter/
│   └── FacturacionAdapter.java
├── client/
│   ├── core/
│   │   ├── ArticuloClient.java
│   │   ├── ClienteMovimientoClient.java
│   │   └── facade/
│   │       ├── FacturacionClient.java
│   │       └── TransaccionFacturaProgramaDiaClient.java
│   └── pyafipws/
│       └── FacturadorClient.java
├── configuration/
│   └── IsolateServiceConfiguration.java
├── controller/ 
│   └── RellenadorController.java 
├── model/ 
│   └── dto/ 
│       ├── core/
│       │   └── FacturacionDto.java
│       ├── web/
│       │   └── ProductDto.java
│       ├── ArticuloDto.java 
│       ├── ArticuloMovimientoDto.java 
│       ├── ClienteDto.java 
│       ├── ClienteMovimientoDto.java 
│       ├── ComprobanteDto.java 
│       ├── CuentaDto.java 
│       ├── DatoUnaFacturaDto.java 
│       └── FacturaResponseDto.java 
├── service/
│   ├── RellenadorService.java
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
### Rellenador
- **Endpoint**: `/api/isolate/rellenador/complete/{letraComprobante}/{puntoVenta}/{numeroComprobanteDesde}/{numeroComprobanteHasta}`
- **Method**: GET
- **Description**: Validates and completes missing invoice numbers in a given range
- **Parameters**:
  - `letraComprobante`: Invoice letter type
  - `puntoVenta`: Sales point ID
  - `numeroComprobanteDesde`: Starting invoice number
  - `numeroComprobanteHasta`: Ending invoice number

## Building and Running

### Prerequisites
- JDK 24
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
   java -jar target/eterea-isolate-service-0.4.0.jar
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

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.