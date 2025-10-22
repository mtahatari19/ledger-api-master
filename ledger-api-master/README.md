# LedgerX

Financial Ledger Management System built with Quarkus and H2 Database.

## Tech Stack

- **Language/Runtime**: Java 21, Quarkus 3.6.0
- **Architecture**: ECB (Entity → Control → Boundary)
- **Communication**: JAX-RS RESTful Services (RESTEasy Reactive)
- **Security**: OIDC/Keycloak (JWT Bearer, Resource Server)
- **Database**: H2 (development), Hibernate ORM with Panache, Liquibase migrations
- **Documentation**: SmallRye OpenAPI (Swagger UI)
- **Build**: Maven
- **Testing**: JUnit 5, REST Assured, Testcontainers
- **I18N**: MessageSource with properties files
- **Dependency Injection**: CDI (Contexts and Dependency Injection)
- **Mapping**: MapStruct for DTO-Entity mapping

## Architecture

This application follows ECB (Entity-Control-Boundary) architecture with one-way dependencies:

- **Entity**: Domain objects with no dependencies (extends `AuditableEntity`)
- **Control**: Business logic that depends on Entity (extends `AbstractBaseService`)
- **Boundary**: REST controllers that depend on Control (extends `BaseController`)

### Core Module

The project includes a reusable `core` module that provides:
- `BaseApi`: Generic interface for CRUD operations
- `BaseController`: Abstract base controller implementation
- `AbstractBaseService`: Abstract base service with transaction management
- `BaseRepository`: Base Panache repository interface
- `AuditableEntity`: Base entity with auditing fields
- Exception handling and validation

## Prerequisites

- Java 21+
- Maven 3.6+
- H2 Database (embedded, no setup required)
- Keycloak (for OIDC authentication)

## Getting Started

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ledger-api
   ```

2. **Set up Keycloak (Optional)**
    - Install and start Keycloak
    - Create realm: `ledgerx`
    - Configure OIDC settings in `application.yml`

3. **Build and run**
   ```bash
   mvn clean compile
   mvn quarkus:dev
   ```

4. **Access the application**
   - API Base URL: http://localhost:8081/api/v1
   - Swagger UI: http://localhost:8081/swagger-ui
   - OpenAPI Spec: http://localhost:8081/api-docs

## API Endpoints

The application provides RESTful APIs for the following entities:

- **Accounts**: `/api/v1/accounts`
- **Currencies**: `/api/v1/currencies`
- **Products**: `/api/v1/products`
- **Product Types**: `/api/v1/product-types`
- **Plans**: `/api/v1/plans`
- **Account Groups**: `/api/v1/account-groups`
- **Accounting Relation Types**: `/api/v1/accounting-relation-types`

### CRUD Operations

All endpoints support standard CRUD operations:
- `GET /api/v1/{resource}` - List all resources (with pagination)
- `GET /api/v1/{resource}/{id}` - Get resource by ID
- `POST /api/v1/{resource}` - Create new resource
- `PUT /api/v1/{resource}/{id}` - Update resource
- `DELETE /api/v1/{resource}/{id}` - Delete resource

## Configuration

### Application Configuration

The application uses `application.yml` for configuration:

```yaml
quarkus:
  application:
    name: ledgerx
  datasource:
    db-kind: h2
    jdbc:
      url: jdbc:h2:mem:ledgerx;DB_CLOSE_DELAY=-1;MODE=MySQL
  http:
    port: 8081
    root-path: /api/v1
  swagger-ui:
    path: /swagger-ui
  smallrye-openapi:
    path: /api-docs
```

### Environment Variables

Key configuration properties can be overridden via environment variables:

- Database connection: `quarkus.datasource.*`
- OIDC settings: `quarkus.oidc.*`
- Server port: `quarkus.http.port`

## Database Migrations

Liquibase is used for database schema management:

```bash
# Update database
mvn liquibase:update

# Generate diff changelog
mvn liquibase:diff

# Rollback
mvn liquibase:rollback -Dliquibase.rollbackCount=1
```

## Testing

```bash
# Run all tests
mvn test

# Run integration tests
mvn verify

# Run in development mode
mvn quarkus:dev
```

## Development

### Package Structure

```
ledger-api/
├── core/                    # Reusable core module
│   └── src/main/java/com/ledger/core/
│       ├── boundary/        # Base API interfaces and controllers
│       ├── control/         # Base service implementations
│       ├── entity/          # Base entities and repositories
│       ├── exceptions/      # Exception handling
│       └── filtering/       # Query parameter parsing
└── ledgerx/                 # Main application module
    └── src/main/java/com/ledgerx/
        ├── account/         # Account management
        ├── currency/        # Currency management
        ├── product/         # Product management
        ├── producttype/     # Product type management
        ├── plan/            # Plan management
        ├── accountGroup/    # Account group management
        └── accountingrelationtype/ # Accounting relation type management
```

### Adding New Features

1. Create entities in `{module}/entity` package (extend `AuditableEntity`)
2. Create repositories in `{module}/boundary` package (implement `BaseRepository`)
3. Create services in `{module}/control` package (extend `AbstractBaseService`)
4. Create API interfaces in `{module}/boundary/api` package (extend `BaseApi`)
5. Create controllers in `{module}/boundary/http` package (extend `BaseController`)
6. Create DTOs in `{module}/boundary/dto` package with OpenAPI annotations
7. Create mappers in `{module}/boundary/mapper` package (implement `BaseMapper`)
8. Add Liquibase migrations in `src/main/resources/db/changelog/`

### Core Functionality

All components are designed to use the core functionality:

- **Controllers**: Extend `BaseController` and implement their respective API interfaces
- **Services**: Extend `AbstractBaseService` and implement only the required mapping methods
- **Repositories**: Implement `BaseRepository` with no custom methods
- **APIs**: Extend `BaseApi` interface

## Security

The application uses OIDC Resource Server with JWT tokens from Keycloak:

- All endpoints (except health checks and API docs) require authentication
- JWT tokens are validated against Keycloak
- Roles are extracted from `realm_access.roles` claim
- Method-level security is enabled with `@RolesAllowed`

## Internationalization

The application supports multiple languages through MessageSource:

- Default: English (`messages.properties`)
- Persian: (`messages_fa.properties`)
- Add new languages by creating `messages_{locale}.properties`

## Build and Deployment

### Development Mode
```bash
mvn quarkus:dev
```

### Production Build
```bash
mvn clean package
java -jar ledgerx/target/quarkus-app/quarkus-run.jar
```

### Native Build
```bash
mvn clean package -Pnative
./ledgerx/target/ledgerx-1.0.0-SNAPSHOT-runner
```

## Features

- ✅ **Full CRUD Operations**: All endpoints support complete CRUD functionality
- ✅ **OpenAPI Documentation**: Auto-generated Swagger UI documentation
- ✅ **Auditing**: Automatic creation and update timestamps
- ✅ **Validation**: Bean validation with custom error messages
- ✅ **Error Handling**: Comprehensive exception handling
- ✅ **Pagination**: Built-in pagination support
- ✅ **Filtering**: Query parameter filtering capabilities
- ✅ **Transaction Management**: Automatic transaction boundaries
- ✅ **Dependency Injection**: CDI-based dependency injection
- ✅ **Hot Reload**: Development mode with hot reload

## License

MIT License