# Firefly Callback Management Platform

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8+-red.svg)](https://maven.apache.org/)

> **Enterprise-grade outbound webhook management platform for sending real-time events from Firefly to third-party systems**

## 📋 Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Architecture](#architecture)
- [Quick Start](#quick-start)
- [Documentation](#documentation)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Building & Running](#building--running)
- [Testing](#testing)
- [License](#license)

## 🎯 Overview

The **Firefly Callback Management Platform** is a reactive, event-driven microservice that enables Firefly to send real-time webhooks to external systems. It provides a complete solution for:

- **Dynamic Event Subscription**: Subscribe to Kafka topics at runtime without redeployment
- **Intelligent Event Routing**: Route events to multiple HTTP endpoints based on event types
- **Reliable Delivery**: Circuit breakers, exponential backoff retries, and execution tracking
- **Security First**: Domain authorization, HMAC signatures, and HTTPS enforcement
- **Multi-Tenant**: Full tenant isolation and configuration
- **Production Ready**: Comprehensive monitoring, metrics, and observability

### Use Cases

- **CRM Integration**: Send customer lifecycle events to Salesforce, HubSpot, or custom CRMs
- **Analytics**: Stream events to data warehouses or analytics platforms
- **Notifications**: Trigger external notification systems on business events
- **Workflow Automation**: Integrate with Zapier, Make, or custom automation platforms
- **Audit & Compliance**: Forward events to external audit systems

## ✨ Key Features

### 🔄 Dynamic Event Subscription
- Subscribe to Kafka topics through REST API
- No code changes or redeployment required
- Support for event type patterns (`customer.*`, `order.completed`)
- Automatic listener registration via `lib-common-eda`

### 🎯 Intelligent Routing
- Route events to multiple HTTP endpoints
- Filter expressions for fine-grained control
- Event type matching with wildcard support
- Parallel callback execution

### 🛡️ Enterprise Security
- **Domain Authorization**: Whitelist-based callback URL validation
- **HMAC Signatures**: SHA-256 signed payloads for webhook verification
- **HTTPS Enforcement**: Configurable per domain
- **Path Restrictions**: Control allowed URL paths
- **Rate Limiting**: Per-domain callback rate limits

### 🔁 Reliability & Resilience
- **Circuit Breaker**: Per-configuration isolation using Resilience4j
- **Exponential Backoff**: Configurable retry delays with multipliers
- **Timeout Management**: Per-callback timeout configuration
- **Execution Tracking**: Complete audit trail of all callback attempts

### 📊 Observability
- **Prometheus Metrics**: Circuit breaker states, retry counts, execution times
- **Spring Boot Actuator**: Health checks, info endpoints
- **Structured Logging**: JSON-formatted logs with correlation IDs
- **Execution History**: Queryable database of all callback attempts

### 🚀 Performance
- **Reactive Architecture**: Built on Spring WebFlux and Project Reactor
- **Non-Blocking I/O**: R2DBC for database, WebClient for HTTP
- **Connection Pooling**: Optimized database and HTTP client pools
- **Async Processing**: Event-driven, non-blocking execution

## 🏗️ Architecture

```
┌─────────────────┐
│ Firefly Services│
│  (Customer,     │
│   Loan, etc.)   │
└────────┬────────┘
         │ Publish Events
         ▼
┌─────────────────┐
│  Apache Kafka   │
│   (Topics)      │
└────────┬────────┘
         │ Consume
         ▼
┌─────────────────────────────────────────────────────────┐
│         Callback Management Platform                    │
│                                                         │
│  ┌──────────────┐    ┌──────────────┐                   │
│  │   Dynamic    │───▶│   Callback   │                   │
│  │   Listener   │    │    Router    │                   │
│  │   Factory    │    └──────┬───────┘                   │
│  └──────────────┘           │                           │
│                             ▼                           │
│                    ┌────────────────┐                   │
│                    │   Dispatcher   │                   │
│                    │  (Circuit      │                   │
│                    │   Breaker +    │                   │
│                    │   Retry)       │                   │
│                    └────────┬───────┘                   │
│                             │                           │
│  ┌──────────────┐           │      ┌──────────────┐     │
│  │   Domain     │◀──────────┤      │  Execution   │     │
│  │Authorization │           │      │   Tracker    │     │
│  └──────────────┘           │      └──────────────┘     │
│                             │                           │
│                             ▼                           │
│                    ┌────────────────┐                   │
│                    │   PostgreSQL   │                   │
│                    │   (R2DBC)      │                   │
│                    └────────────────┘                   │
└─────────────────────────────────────────────────────────┘
         │ HTTP Callbacks
         ▼
┌─────────────────┐
│  Third-Party    │
│    Systems      │
│ (CRM, Analytics,│
│  Webhooks, etc.)│
└─────────────────┘
```

**Event Flow:**
1. Firefly services publish events to Kafka topics
2. Dynamic listeners consume events based on database subscriptions
3. Callback router finds matching configurations
4. Domain authorization validates callback URLs
5. Dispatcher executes HTTP callbacks with retry/circuit breaker
6. Execution results are tracked in PostgreSQL

For detailed architecture documentation, see [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md).

## 🚀 Quick Start

### Prerequisites

- **Java 21** or higher
- **Maven 3.8+**
- **PostgreSQL 14+**
- **Apache Kafka 3.0+** (or Confluent Platform 7.5+)
- **Docker** (optional, for running dependencies)

### 1. Clone the Repository

```bash
git clone https://github.com/firefly-oss/common-platform-callbacks-mgmt.git
cd common-platform-callbacks-mgmt
```

### 2. Start Dependencies (Docker)

```bash
# Start PostgreSQL and Kafka
docker-compose up -d postgres kafka
```

### 3. Configure Environment

```bash
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=callbacks_db
export DB_USERNAME=firefly
export DB_PASSWORD=firefly
export KAFKA_BOOTSTRAP_SERVERS=localhost:9092
```

### 4. Build the Project

```bash
mvn clean install
```

### 5. Run the Application

```bash
cd common-platform-callbacks-mgmt-web
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

### 6. Verify Installation

```bash
# Check health
curl http://localhost:8080/actuator/health

# Access Swagger UI
open http://localhost:8080/swagger-ui.html
```

For a complete quickstart guide, see [docs/QUICKSTART_GUIDE.md](docs/QUICKSTART_GUIDE.md).

## 📚 Documentation

| Document | Description |
|----------|-------------|
| [**Callback System Summary**](docs/CALLBACK_SYSTEM_SUMMARY.md) | **⭐ START HERE** - Executive summary of the complete callback system implementation |
| [Callback System Reference](docs/CALLBACK_SYSTEM_REFERENCE.md) | Complete reference - domains, configurations, HMAC, retries, metadata, best practices |
| [Callback Examples](docs/CALLBACK_EXAMPLES.md) | Practical examples - Salesforce, Zapier, OAuth, custom integrations, testing |
| [Architecture Deep Dive](docs/ARCHITECTURE.md) | Complete architectural overview, design patterns, and data models |
| [Quickstart Guide](docs/QUICKSTART_GUIDE.md) | Step-by-step guide to get started quickly |
| [Testing Guide](docs/TESTING_GUIDE.md) | Comprehensive testing strategies and examples |

## 🛠️ Technology Stack

### Core Framework
- **Spring Boot 3.2.2** - Application framework
- **Spring WebFlux** - Reactive web framework
- **Project Reactor** - Reactive programming library

### Data Layer
- **Spring Data R2DBC** - Reactive database access
- **PostgreSQL** - Primary database
- **Flyway** - Database migrations

### Messaging
- **Apache Kafka** - Event streaming platform
- **lib-common-eda** - Firefly's event-driven architecture library

### Resilience
- **Resilience4j** - Circuit breaker and retry patterns
- **Spring Retry** - Declarative retry support

### Observability
- **Spring Boot Actuator** - Production-ready features
- **Micrometer** - Metrics facade
- **Prometheus** - Metrics collection

### Development
- **Lombok** - Boilerplate reduction
- **MapStruct** - DTO-Entity mapping
- **OpenAPI 3** - API documentation

### Testing
- **JUnit 5** - Testing framework
- **Testcontainers** - Integration testing with Docker
- **WireMock** - HTTP mocking
- **Reactor Test** - Reactive testing utilities

## 📁 Project Structure

```
common-platform-callbacks-mgmt/
├── common-platform-callbacks-mgmt-interfaces/    # DTOs, Enums, API contracts
│   └── src/main/java/.../interfaces/
│       ├── dto/                                  # Data Transfer Objects
│       └── enums/                                # Enumerations
│
├── common-platform-callbacks-mgmt-models/        # Entities, Repositories
│   └── src/main/
│       ├── java/.../models/
│       │   ├── entity/                           # R2DBC entities
│       │   └── repository/                       # R2DBC repositories
│       └── resources/db/migration/               # Flyway migrations
│
├── common-platform-callbacks-mgmt-core/          # Business logic
│   └── src/main/java/.../core/
│       ├── filters/                              # FilterRequest, FilterUtils
│       ├── listener/                             # Dynamic event listeners
│       ├── mapper/                               # MapStruct mappers
│       └── service/                              # Service interfaces & implementations
│
├── common-platform-callbacks-mgmt-web/           # REST API, Configuration
│   └── src/main/
│       ├── java/.../web/
│       │   ├── config/                           # Spring configuration
│       │   └── controller/                       # REST controllers
│       └── resources/
│           └── application.yml                   # Application configuration
│
└── common-platform-callbacks-mgmt-sdk/           # Client SDK (future)
```

## 🔌 API Endpoints

### Event Subscriptions
```
POST   /api/v1/event-subscriptions/filter     # Filter subscriptions (paginated)
POST   /api/v1/event-subscriptions            # Create subscription
GET    /api/v1/event-subscriptions/{id}       # Get by ID
PUT    /api/v1/event-subscriptions/{id}       # Update subscription
DELETE /api/v1/event-subscriptions/{id}       # Delete subscription
```

### Authorized Domains
```
POST   /api/v1/authorized-domains/filter              # Filter domains (paginated)
POST   /api/v1/authorized-domains                     # Create domain
PUT    /api/v1/authorized-domains/{id}                # Update domain
POST   /api/v1/authorized-domains/{domain}/verify     # Verify domain (requires verificationMethod param)
DELETE /api/v1/authorized-domains/{id}                # Delete domain (requires domain param)
```

### Callback Configurations
```
POST   /api/v1/callback-configurations/filter # Filter configurations (paginated)
POST   /api/v1/callback-configurations        # Create configuration
GET    /api/v1/callback-configurations/{id}   # Get by ID
PUT    /api/v1/callback-configurations/{id}   # Update configuration
DELETE /api/v1/callback-configurations/{id}   # Delete configuration
```

### Callback Executions
```
POST   /api/v1/callback-executions/filter                                            # Filter executions (paginated)
GET    /api/v1/callback-executions                                                   # List all executions
GET    /api/v1/callback-executions/{id}                                              # Get by ID
GET    /api/v1/callback-executions/by-configuration/{configId}                       # List by configuration
GET    /api/v1/callback-executions/by-status?status={status}                         # List by status
GET    /api/v1/callback-executions/pending-retries                                   # List pending retries
GET    /api/v1/callback-executions/recent?configurationId={id}&duration={duration}   # List recent executions
```

**Full API documentation available at:** `http://localhost:8080/swagger-ui.html`

## ⚙️ Configuration

Key configuration properties in `application.yml`:

```yaml
# Database
spring:
  r2dbc:
    url: r2dbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:callbacks_db}
    username: ${DB_USERNAME:firefly}
    password: ${DB_PASSWORD:firefly}

# Kafka (via Firefly EDA)
firefly:
  eda:
    consumer:
      enabled: true
    consumers:
      kafka:
        default:
          bootstrap-servers: ${FIREFLY_KAFKA_BOOTSTRAP_SERVERS}
          properties:
            group.id: callbacks-mgmt-consumer

  # Callbacks Configuration
  callbacks:
    circuit-breaker:
      failure-rate-threshold: ${CALLBACK_CB_FAILURE_RATE:50}
      wait-duration-in-open-state-ms: ${CALLBACK_CB_WAIT_DURATION:60000}

    retry:
      max-attempts: ${CALLBACK_RETRY_MAX_ATTEMPTS:3}
      initial-delay-ms: ${CALLBACK_RETRY_INITIAL_DELAY:1000}
      max-delay-ms: ${CALLBACK_RETRY_MAX_DELAY:60000}
```

See [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) for complete configuration reference.

## 🔨 Building & Running

### Build

```bash
# Build all modules
mvn clean install

# Skip tests
mvn clean install -DskipTests

# Build specific module
mvn clean install -pl common-platform-callbacks-mgmt-web
```

### Run

```bash
# Run with Maven
cd common-platform-callbacks-mgmt-web
mvn spring-boot:run

# Run with Java
java -jar common-platform-callbacks-mgmt-web/target/common-platform-callbacks-mgmt-web-1.0.0-SNAPSHOT.jar

# Run with Docker
docker build -t firefly/callbacks-mgmt:latest .
docker run -p 8080:8080 firefly/callbacks-mgmt:latest
```

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CallbackManagementEndToEndTest

# Run integration tests only
mvn verify -P integration-tests

# Generate coverage report
mvn clean test jacoco:report
```

**Test Coverage:**
- Unit Tests: Service layer, mappers, utilities
- Integration Tests: Repository layer with Testcontainers
- End-to-End Tests: Full flow with Kafka, PostgreSQL, and WireMock

See [docs/TESTING_GUIDE.md](docs/TESTING_GUIDE.md) for detailed testing documentation.

## 📄 License

**© 2025 Firefly Software Solutions Inc. All rights reserved.**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

---

**Developed with ❤️ by the Firefly Team**

