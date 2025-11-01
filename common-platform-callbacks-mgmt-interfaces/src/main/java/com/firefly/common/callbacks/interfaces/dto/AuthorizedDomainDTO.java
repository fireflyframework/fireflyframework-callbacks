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
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

/**
 * DTO for managing authorized callback domains.
 * Only callbacks to authorized domains are allowed.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizedDomainDTO {

    /**
     * Unique identifier for the authorized domain.
     */
    private UUID id;

    /**
     * Domain name (e.g., "api.example.com", "*.example.com" for wildcard, "localhost:8080").
     */
    @NotBlank(message = "Domain is required")
    private String domain;

    /**
     * Organization or owner of this domain.
     */
    private String organization;

    /**
     * Contact email for this domain.
     */
    private String contactEmail;

    /**
     * Whether this domain is verified (e.g., via DNS TXT record or challenge).
     */
    @Builder.Default
    private Boolean verified = false;

    /**
     * Verification method used (DNS, HTTP, EMAIL).
     */
    private String verificationMethod;

    /**
     * Verification token for domain verification.
     */
    private String verificationToken;

    /**
     * When the domain was verified.
     */
    private Instant verifiedAt;

    /**
     * Whether this domain is active and can receive callbacks.
     */
    @Builder.Default
    private Boolean active = true;

    /**
     * Allowed URL paths (patterns). Null/empty means all paths allowed.
     * Example: ["/webhooks/*", "/api/v1/callbacks/*"]
     */
    private String[] allowedPaths;

    /**
     * Maximum callbacks per minute for this domain (rate limiting).
     */
    @Builder.Default
    private Integer maxCallbacksPerMinute = 100;

    /**
     * IP whitelist for this domain (optional additional security).
     */
    private String[] ipWhitelist;

    /**
     * Whether to require HTTPS for this domain.
     */
    @Builder.Default
    private Boolean requireHttps = true;

    /**
     * Tenant ID for multi-tenancy.
     */
    private String tenantId;

    /**
     * Additional notes about this domain.
     */
    private String notes;

    /**
     * Metadata for the domain.
     */
    private Map<String, Object> metadata;

    /**
     * Domain expiration date (for temporary authorizations).
     */
    private Instant expiresAt;

    /**
     * Last time a callback was sent to this domain.
     */
    private Instant lastCallbackAt;

    /**
     * Total callbacks sent to this domain.
     */
    @Builder.Default
    private Long totalCallbacks = 0L;

    /**
     * Total failed callbacks to this domain.
     */
    @Builder.Default
    private Long totalFailed = 0L;

    /**
     * Creation timestamp.
     */
    private Instant createdAt;

    /**
     * Last update timestamp.
     */
    private Instant updatedAt;

    /**
     * User who authorized the domain.
     */
    private String authorizedBy;
}
