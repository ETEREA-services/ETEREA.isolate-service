# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

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