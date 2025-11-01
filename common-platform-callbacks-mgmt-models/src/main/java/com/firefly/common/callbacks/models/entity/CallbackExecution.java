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

import com.firefly.common.callbacks.interfaces.enums.CallbackExecutionStatus;
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
 * Entity for callback execution tracking.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("callback_executions")
public class CallbackExecution {

    @Id
    private UUID id;

    @Column("configuration_id")
    private UUID configurationId;

    @Column("event_type")
    private String eventType;

    @Column("source_event_id")
    private UUID sourceEventId;

    private CallbackExecutionStatus status;

    @Column("request_payload")
    private String requestPayload;

    @Column("response_status_code")
    private Integer responseStatusCode;

    @Column("response_body")
    private String responseBody;

    @Column("request_headers")
    private String requestHeaders;

    @Column("response_headers")
    private String responseHeaders;

    @Column("attempt_number")
    private Integer attemptNumber;

    @Column("max_attempts")
    private Integer maxAttempts;

    @Column("error_message")
    private String errorMessage;

    @Column("error_stack_trace")
    private String errorStackTrace;

    @Column("request_duration_ms")
    private Long requestDurationMs;

    @Column("next_retry_at")
    private Instant nextRetryAt;

    @Column("executed_at")
    private Instant executedAt;

    @Column("completed_at")
    private Instant completedAt;

    private String metadata;
}
