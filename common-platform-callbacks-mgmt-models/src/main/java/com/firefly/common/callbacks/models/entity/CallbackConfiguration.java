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

import com.firefly.common.callbacks.interfaces.enums.CallbackStatus;
import com.firefly.common.callbacks.interfaces.enums.HttpMethod;
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
 * Entity for callback configurations.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("callback_configurations")
public class CallbackConfiguration {

    @Id
    private UUID id;

    private String name;
    private String description;
    private String url;

    @Column("http_method")
    private HttpMethod httpMethod;

    private CallbackStatus status;

    @Column("subscribed_event_types")
    private String[] subscribedEventTypes;

    @Column("custom_headers")
    private String customHeaders;

    private String secret;

    @Column("signature_enabled")
    private Boolean signatureEnabled;

    @Column("signature_header")
    private String signatureHeader;

    @Column("max_retries")
    private Integer maxRetries;

    @Column("retry_delay_ms")
    private Integer retryDelayMs;

    @Column("retry_backoff_multiplier")
    private Double retryBackoffMultiplier;

    @Column("timeout_ms")
    private Integer timeoutMs;

    private Boolean active;

    @Column("tenant_id")
    private String tenantId;

    @Column("filter_expression")
    private String filterExpression;

    private String metadata;

    @Column("failure_threshold")
    private Integer failureThreshold;

    @Column("failure_count")
    private Integer failureCount;

    @Column("last_success_at")
    private Instant lastSuccessAt;

    @Column("last_failure_at")
    private Instant lastFailureAt;

    @Column("created_at")
    private Instant createdAt;

    @Column("updated_at")
    private Instant updatedAt;

    @Column("created_by")
    private String createdBy;

    @Column("updated_by")
    private String updatedBy;
}
