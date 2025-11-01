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

package com.firefly.common.callbacks.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

/**
 * Entity for event subscriptions.
 * Represents a dynamic subscription to a messaging system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("event_subscriptions")
public class EventSubscription {

    @Id
    private UUID id;

    private String name;
    private String description;

    @Column("messaging_system_type")
    private String messagingSystemType;

    @Column("connection_config")
    private String connectionConfig;

    @Column("topic_or_queue")
    private String topicOrQueue;

    @Column("consumer_group_id")
    private String consumerGroupId;

    private Boolean active;

    @Column("consumer_properties")
    private String consumerProperties;

    @Column("event_type_patterns")
    private String[] eventTypePatterns;

    @Column("max_concurrent_consumers")
    private Integer maxConcurrentConsumers;

    @Column("polling_interval_ms")
    private Integer pollingIntervalMs;

    @Column("tenant_id")
    private String tenantId;

    private String metadata;

    @Column("last_message_at")
    private Instant lastMessageAt;

    @Column("total_messages_received")
    private Long totalMessagesReceived;

    @Column("total_messages_failed")
    private Long totalMessagesFailed;

    @Column("created_at")
    private Instant createdAt;

    @Column("updated_at")
    private Instant updatedAt;

    @Column("created_by")
    private String createdBy;

    @Column("updated_by")
    private String updatedBy;
}
