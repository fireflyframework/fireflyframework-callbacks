/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.firefly.common.callbacks.interfaces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

/**
 * DTO for configuring dynamic event subscriptions.
 * This allows the system to subscribe to new topics/queues at runtime.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventSubscriptionDTO {

    /**
     * Unique identifier for the subscription.
     */
    private UUID id;

    /**
     * Name of the subscription (e.g., "Customer Service Events").
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * Description of the subscription.
     */
    private String description;

    /**
     * Messaging system type (KAFKA, RABBITMQ, PULSAR, AWS_SQS, etc.).
     */
    @NotBlank(message = "Messaging system type is required")
    private String messagingSystemType;

    /**
     * Connection configuration for the messaging system.
     * Example for Kafka: {"bootstrap.servers": "localhost:9092", "group.id": "callbacks-consumer"}
     * Example for RabbitMQ: {"host": "localhost", "port": "5672", "username": "guest"}
     */
    @NotNull(message = "Connection config is required")
    private Map<String, String> connectionConfig;

    /**
     * Topic/queue name to subscribe to.
     * For Kafka: topic name
     * For RabbitMQ: queue name
     * For SQS: queue URL
     */
    @NotBlank(message = "Topic/queue name is required")
    private String topicOrQueue;

    /**
     * Consumer group ID (for systems that support it like Kafka).
     */
    private String consumerGroupId;

    /**
     * Whether this subscription is active.
     */
    @Builder.Default
    private Boolean active = true;

    /**
     * Additional consumer properties specific to the messaging system.
     */
    private Map<String, String> consumerProperties;

    /**
     * Event type patterns to match (wildcards supported).
     * Example: ["customer.*", "loan.approved"]
     */
    private String[] eventTypePatterns;

    /**
     * Maximum number of concurrent consumers for this subscription.
     */
    @Builder.Default
    private Integer maxConcurrentConsumers = 1;

    /**
     * Polling interval in milliseconds (for pull-based systems).
     */
    @Builder.Default
    private Integer pollingIntervalMs = 1000;

    /**
     * Tenant ID for multi-tenancy.
     */
    private String tenantId;

    /**
     * Metadata for the subscription.
     */
    private Map<String, Object> metadata;

    /**
     * Last time a message was received from this subscription.
     */
    private Instant lastMessageAt;

    /**
     * Total messages received from this subscription.
     */
    @Builder.Default
    private Long totalMessagesReceived = 0L;

    /**
     * Total messages failed to process.
     */
    @Builder.Default
    private Long totalMessagesFailed = 0L;

    /**
     * Creation timestamp.
     */
    private Instant createdAt;

    /**
     * Last update timestamp.
     */
    private Instant updatedAt;

    /**
     * User who created the subscription.
     */
    private String createdBy;

    /**
     * User who last updated the subscription.
     */
    private String updatedBy;
}
