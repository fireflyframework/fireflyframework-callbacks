# Firefly Framework - Callbacks

[![CI](https://github.com/fireflyframework/fireflyframework-callbacks/actions/workflows/ci.yml/badge.svg)](https://github.com/fireflyframework/fireflyframework-callbacks/actions/workflows/ci.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-21%2B-orange.svg)](https://openjdk.org)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)

> Outbound callback management platform for dispatching events to external systems with circuit breakers, retry logic, and domain authorization.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Quick Start](#quick-start)
- [Configuration](#configuration)
- [Documentation](#documentation)
- [Contributing](#contributing)
- [License](#license)

## Overview

Firefly Framework Callbacks provides an outbound webhook/callback management system for dispatching events to external systems. It enables applications to register callback configurations, subscribe to events, manage authorized domains, and track callback execution history with comprehensive retry and circuit breaker support.

The project is structured as a multi-module build with five sub-modules: interfaces (DTOs and enums), models (database entities and repositories), core (services and business logic), SDK (client library), and web (REST controllers). The core module includes callback dispatching with routing, domain authorization, and event subscription management.

The callback dispatcher handles reliable event delivery to registered endpoints with configurable retry policies, execution tracking, and authorized domain validation for security.

## Features

- Callback configuration management with CRUD operations
- Event subscription system for selective event routing
- Authorized domain management for endpoint security
- Callback dispatcher with reliable event delivery
- Callback router for multi-endpoint event distribution
- Dynamic listener factory for runtime event binding
- Execution tracking with status history (pending, success, failed)
- Filtering and pagination for configuration and execution queries
- Domain authorization service for endpoint validation
- REST controllers for callbacks, subscriptions, domains, and executions
- Multi-module architecture: interfaces, models, core, SDK, web

## Requirements

- Java 21+
- Spring Boot 3.x
- Maven 3.9+
- PostgreSQL database (for callback persistence)

## Installation

The callbacks library is a multi-module project. Include the modules you need:

```xml
<!-- Core callback services -->
<dependency>
    <groupId>org.fireflyframework</groupId>
    <artifactId>fireflyframework-callbacks-core</artifactId>
    <version>26.01.01</version>
</dependency>

<!-- DTOs and interfaces -->
<dependency>
    <groupId>org.fireflyframework</groupId>
    <artifactId>fireflyframework-callbacks-interfaces</artifactId>
    <version>26.01.01</version>
</dependency>

<!-- SDK for client integration -->
<dependency>
    <groupId>org.fireflyframework</groupId>
    <artifactId>fireflyframework-callbacks-sdk</artifactId>
    <version>26.01.01</version>
</dependency>
```

## Quick Start

```java
import org.fireflyframework.callbacks.core.service.CallbackDispatcher;

@Service
public class OrderEventPublisher {

    private final CallbackDispatcher callbackDispatcher;

    public Mono<Void> publishOrderCreated(Order order) {
        return callbackDispatcher.dispatch("order.created", order);
    }
}
```

## Configuration

```yaml
firefly:
  callbacks:
    retry:
      max-attempts: 3
      backoff-multiplier: 2
      initial-delay: 1s
    domain-authorization:
      enabled: true

spring:
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/callbacks
```

## Documentation

Additional documentation is available in the [docs/](docs/) directory:

- [Quickstart Guide](docs/QUICKSTART_GUIDE.md)
- [Architecture](docs/ARCHITECTURE.md)
- [Callback System Reference](docs/CALLBACK_SYSTEM_REFERENCE.md)
- [Callback System Summary](docs/CALLBACK_SYSTEM_SUMMARY.md)
- [Callback Examples](docs/CALLBACK_EXAMPLES.md)
- [Testing Guide](docs/TESTING_GUIDE.md)

## Contributing

Contributions are welcome. Please read the [CONTRIBUTING.md](CONTRIBUTING.md) guide for details on our code of conduct, development process, and how to submit pull requests.

## License

Copyright 2024-2026 Firefly Software Solutions Inc.

Licensed under the Apache License, Version 2.0. See [LICENSE](LICENSE) for details.
