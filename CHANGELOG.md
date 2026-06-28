# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.7.1] - 2026-06-28
### Fixed
- fix(test): Corregido valor hardcodeado de `comprobanteId` en tests de `RellenadorController` y `RellenadorService` (cambiado de `853` a `1`) (Fuente: `git diff HEAD`, `RellenadorControllerTest.java`, `RellenadorServiceTest.java`)

## [0.7.0] - 2026-06-28
### Added
- feat(endpoint): Nuevo parámetro `comprobanteId` en endpoint `auto-completa` para especificar el comprobante a buscar (Fuente: `git diff HEAD`, `RellenadorController.java`)
- feat(deps): Nueva dependencia `spring-boot-starter-webmvc-test` para tests de WebMvc (Fuente: `git diff HEAD`, `pom.xml`)
- feat(deps): Nueva dependencia `commons-fileupload` 1.6.0 en `dependencyManagement` (Fuente: `git diff HEAD`, `pom.xml`)
- feat(refactor): Uso de `@RequiredArgsConstructor` de Lombok en `RellenadorService`, eliminando constructor explícito (Fuente: `git diff HEAD`, `RellenadorService.java`)
- feat(ci): Unificación de jobs de build de imágenes JVM (`build-jvm-image` → `build-image`) (Fuente: `git diff HEAD`, `maven.yml`)

### Changed
- chore(deps): Migración de Spring Boot 3.5.6 a 4.1.0 (Fuente: `git diff HEAD`, `pom.xml`)
- chore(deps): Actualización de Java 24 a 25 (Fuente: `git diff HEAD`, `pom.xml`, `Dockerfile`)
- chore(deps): Actualización de Spring Cloud 2025.0.0 a 2025.1.2 (Fuente: `git diff HEAD`, `pom.xml`)
- chore(deps): Actualización de SpringDoc OpenAPI 2.8.10 a 3.0.3 (Fuente: `git diff HEAD`, `pom.xml`)
- chore(deps): Actualización de commons-lang3 3.18.0 a 3.20.0 (Fuente: `git diff HEAD`, `pom.xml`)
- chore(deps): Actualización de imágenes Docker a Eclipse Temurin 25 (Fuente: `git diff HEAD`, `Dockerfile`)
- chore(deps): Actualización de Actions en CI/CD (checkout v6, setup-java v5, cache v5, docker/login v4, docker/metadata v6, docker/setup-buildx v4, docker/build-push v7, upload-pages-artifact v4, deploy-pages v5) (Fuente: `git diff HEAD`, `.github/workflows/*.yml`)
- chore(config): Eliminada configuración `executable=true` del plugin spring-boot-maven-plugin (Fuente: `git diff HEAD`, `pom.xml`)
- fix(dto): Cambio de formato de fecha `Z` a `XX` en DTOs (`ArticuloMovimientoDto`, `ClienteDto`, `ClienteMovimientoDto`) para compatibilidad con Java 25 (Fuente: `git diff HEAD`)
- chore(ci): Simplificación del pipeline de generación de documentación, eliminando el directorio `_site` intermedio (Fuente: `git diff HEAD`, `generate-docs.yml`)
- chore(test): Actualización de import en `RellenadorControllerTest` para `spring-boot-webmvc-test` (Fuente: `git diff HEAD`)

### Removed
- chore(ci): Eliminado job de build de imagen nativa GraalVM (Fuente: `git diff HEAD`, `maven.yml`)

## [0.6.1] - 2025-09-21
### Changed
- chore(deps): Actualización de Spring Boot a 3.5.6.
- chore(deps): Actualización de SpringDoc OpenAPI a 2.8.10.

## [0.6.0] - 2025-08-05
### Added
- feat(model): Añadido `ProductDto` para representar información de productos en el sistema.
- feat(controller): Nuevo endpoint para generación de nota de crédito (`NotaCreditoController`).
- feat(service): Servicio `NotaCreditoService` para lógica de generación de notas de crédito.
- feat(client): Nuevos Feign clients (`ComprobanteClient`, métodos extendidos en `OrderNoteClient`, `FacturacionClient`).

### Changed
- chore(deps): Actualización de Spring Boot a 3.5.4.
- chore(config): Migración de Eureka a Consul en la configuración (`bootstrap.yml`, código).
- chore(docs): Actualización de README y diagramas para reflejar la nueva arquitectura y dependencias.
- refactor(service): Simplificación de logging y uso de métodos `jsonify` en DTOs.

### Fixed
- fix(test): Actualización de tests para reflejar nuevos métodos y dependencias.

## [0.5.0] - 2025-07-14
### Added
- feat: Soporte experimental para despliegue nativo con GraalVM (`Dockerfile.graalvm`, configuración en `pom.xml`)
- feat: Unificación de configuración, migrando de `bootstrap.yml` a `application.yml`
### Changed
- fix: Corrección de nombres de servicios en Dockerfile
### Maintenance
- test: Deshabilitados temporalmente los tests automáticos

## [0.4.0] - 2025-07-09

### Features
- feat(service): refactorizar lógica de facturación e integrar nuevos clientes
- feat(docs): agregar generación automatizada de documentación y actualizar proyecto
- feat(service): implementa optimizaciones y mejoras del servicio de aislamiento

## [0.3.0] - 2025-06-30
- feat: Añadida autenticación por OAuth2
- fix: Corregido error en la validación de emails