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

import com.firefly.common.callbacks.interfaces.enums.CallbackStatus;
import com.firefly.common.callbacks.interfaces.enums.HttpMethod;
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
 * DTO for callback configuration.
 * Represents the configuration for sending outbound webhooks to third parties.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallbackConfigurationDTO {

    /**
     * Unique identifier for the callback configuration.
     */
    private UUID id;

    /**
     * Name of the callback configuration.
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * Description of the callback configuration.
     */
    private String description;

    /**
     * Target URL for the callback endpoint.
     */
    @NotBlank(message = "URL is required")
    private String url;

    /**
     * HTTP method to use for the callback.
     */
    @Builder.Default
    @NotNull(message = "HTTP method is required")
    private HttpMethod httpMethod = HttpMethod.POST;

    /**
     * Status of the callback configuration.
     */
    @Builder.Default
    @NotNull(message = "Status is required")
    private CallbackStatus status = CallbackStatus.ACTIVE;

    /**
     * Array of event types this callback is subscribed to.
     * Examples: "customer.created", "loan.approved", "payment.received"
     */
    @NotNull(message = "Event types are required")
    private String[] subscribedEventTypes;

    /**
     * Custom headers to include in the callback request.
     */
    private Map<String, String> customHeaders;

    /**
     * Secret for HMAC signature generation (if enabled).
     */
    private String secret;

    /**
     * Whether to include HMAC signature in the callback request.
     */
    @Builder.Default
    private Boolean signatureEnabled = false;

    /**
     * Signature header name (e.g., "X-Firefly-Signature").
     */
    private String signatureHeader;

    /**
     * Maximum number of retry attempts for failed callbacks.
     */
    @Builder.Default
    private Integer maxRetries = 3;

    /**
     * Initial retry delay in milliseconds.
     */
    @Builder.Default
    private Integer retryDelayMs = 1000;

    /**
     * Retry backoff multiplier (exponential backoff).
     */
    @Builder.Default
    private Double retryBackoffMultiplier = 2.0;

    /**
     * Timeout for the callback request in milliseconds.
     */
    @Builder.Default
    private Integer timeoutMs = 30000;

    /**
     * Whether the callback is active.
     */
    @Builder.Default
    private Boolean active = true;

    /**
     * Organization or tenant ID that owns this callback configuration.
     */
    private String tenantId;

    /**
     * Optional filter expression (JSONPath or similar) to filter events.
     */
    private String filterExpression;

    /**
     * Metadata for the callback configuration.
     */
    private Map<String, Object> metadata;

    /**
     * Number of consecutive failures before auto-disabling.
     */
    @Builder.Default
    private Integer failureThreshold = 10;

    /**
     * Current failure count.
     */
    @Builder.Default
    private Integer failureCount = 0;

    /**
     * Last successful callback execution time.
     */
    private Instant lastSuccessAt;

    /**
     * Last failed callback execution time.
     */
    private Instant lastFailureAt;

    /**
     * Callback configuration creation time.
     */
    private Instant createdAt;

    /**
     * Callback configuration last update time.
     */
    private Instant updatedAt;

    /**
     * User who created the configuration.
     */
    private String createdBy;

    /**
     * User who last updated the configuration.
     */
    private String updatedBy;
}
