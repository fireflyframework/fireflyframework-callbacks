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

import com.firefly.common.callbacks.interfaces.enums.CallbackExecutionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

/**
 * DTO for callback execution tracking.
 * Represents an attempt to send a callback to a third party.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallbackExecutionDTO {

    /**
     * Unique identifier for the execution.
     */
    private UUID id;

    /**
     * Configuration ID that triggered this execution.
     */
    private UUID configurationId;

    /**
     * Event type that triggered the callback.
     */
    private String eventType;

    /**
     * Event ID from the source event.
     */
    private UUID sourceEventId;

    /**
     * Execution status.
     */
    private CallbackExecutionStatus status;

    /**
     * Request payload sent to the callback URL.
     */
    private Map<String, Object> requestPayload;

    /**
     * HTTP response status code.
     */
    private Integer responseStatusCode;

    /**
     * HTTP response body.
     */
    private String responseBody;

    /**
     * Request headers sent.
     */
    private Map<String, String> requestHeaders;

    /**
     * Response headers received.
     */
    private Map<String, String> responseHeaders;

    /**
     * Execution attempt number (0-indexed).
     */
    @Builder.Default
    private Integer attemptNumber = 0;

    /**
     * Maximum retry attempts configured.
     */
    private Integer maxAttempts;

    /**
     * Error message if execution failed.
     */
    private String errorMessage;

    /**
     * Error stack trace if available.
     */
    private String errorStackTrace;

    /**
     * Duration of the HTTP request in milliseconds.
     */
    private Long requestDurationMs;

    /**
     * Next scheduled retry time (if applicable).
     */
    private Instant nextRetryAt;

    /**
     * Time when execution started.
     */
    private Instant executedAt;

    /**
     * Time when execution completed.
     */
    private Instant completedAt;

    /**
     * Metadata for the execution.
     */
    private Map<String, Object> metadata;
}
