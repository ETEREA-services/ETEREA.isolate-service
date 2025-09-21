# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

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