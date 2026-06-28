# Eterea Isolate Service

![Java](https://img.shields.io/badge/java-25-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen.svg)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.2-yellow.svg)
[![Maven Central](https://img.shields.io/maven-central/v/com.termascacheuta/eterea-isolate-service.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.termascacheuta%22%20AND%20a:%22eterea-isolate-service%22)

## Novedades en la versión 0.7.1

- **Corrección en tests:** Valor hardcodeado de `comprobanteId` corregido de `853` a `1` en pruebas de `RellenadorController` y `RellenadorService`.

## Novedades en la versión 0.7.0

- **Migración a Spring Boot 4.1.0 y Java 25:** Actualización mayor del framework base y la plataforma Java.
- **Breaking API:** Nuevo parámetro `comprobanteId` en endpoint `auto-completa` para especificar el comprobante a buscar.
- **Actualización de Dependencias:** Spring Cloud 2025.1.2, SpringDoc OpenAPI 3.0.3, commons-lang3 3.20.0.
- **Simplificación CI/CD:** Eliminado job de build de imagen nativa GraalVM; imágenes Docker actualizadas a Eclipse Temurin 25.

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
- Java 25
- Spring Boot 4.1.0
- Spring Cloud 2025.1.2
- Spring Cloud Consul Discovery
- Spring Cloud OpenFeign
- Lombok
- Caffeine Cache
- SpringDoc OpenAPI

## Project Structure
```
src/main/java/eterea/isolate/service/
├── adapter/
│   └── FacturacionAdapter.java
├── client/
│   ├── core/
│   │   ├── ArticuloClient.java
│   │   ├── ClienteMovimientoClient.java
│   │   ├── ComprobanteClient.java
│   │   └── facade/
│   │       ├── FacturacionClient.java
│   │       └── TransaccionFacturaProgramaDiaClient.java
│   ├── pyafipws/
│   │   └── FacturadorClient.java
│   └── web/
│       └── OrderNoteClient.java
├── configuration/
│   ├── FeignAuthInterceptor.java
│   └── IsolateServiceConfiguration.java
├── controller/
│   ├── NotaCreditoController.java
│   └── RellenadorController.java
├── model/
│   └── dto/
│       ├── core/
│       │   └── FacturacionDto.java
│       ├── web/
│       │   ├── InformacionPagadorDto.java
│       │   ├── OrderNoteDto.java
│       │   ├── PaymentDto.java
│       │   ├── ProductDto.java
│       │   └── ProductTransactionDto.java
│       ├── ArticuloDto.java
│       ├── ArticuloMovimientoDto.java
│       ├── ClienteDto.java
│       ├── ClienteMovimientoDto.java
│       ├── ComprobanteDto.java
│       ├── CuentaDto.java
│       ├── DatoUnaFacturaDto.java
│       ├── MonedaDto.java
│       └── FacturaResponseDto.java
├── service/
│   ├── NotaCreditoService.java
│   ├── RellenadorService.java
│   └── miscelaneos/
│       └── ToolService.java
└── IsolateServiceApplication.java
```

## Configuration
The service is configured through `application.yml` with the following key properties:
- Server port: Configurable via `APP_PORT` environment variable (default: 8080)
- Consul client configuration for service discovery
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

- **Endpoint**: `/api/isolate/rellenador/auto-completa/{tipoAfipId}/{puntoVenta}/{numeroComprobante}/comprobante/{comprobanteId}/solo-factura/{soloFactura}/dry-run/{dryRun}`
- **Method**: GET
- **Description**: Auto-completes invoice information for a given comprobante
- **Parameters**:
  - `tipoAfipId`: AFIP document type ID
  - `puntoVenta`: Sales point ID
  - `numeroComprobante`: Invoice number
  - `comprobanteId`: Comprobante ID to search for
  - `soloFactura`: If true, only process invoices
  - `dryRun`: If true, run without persisting changes

## Building and Running

### Prerequisites
- JDK 25
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
   java -jar target/eterea.isolate-service.jar
   ```

## Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Validation
- Spring Cloud Starter
- Spring Cloud Consul Discovery
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