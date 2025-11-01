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
 * Entity for authorized callback domains.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("authorized_domains")
public class AuthorizedDomain {

    @Id
    private UUID id;

    private String domain;
    private String organization;

    @Column("contact_email")
    private String contactEmail;

    private Boolean verified;

    @Column("verification_method")
    private String verificationMethod;

    @Column("verification_token")
    private String verificationToken;

    @Column("verified_at")
    private Instant verifiedAt;

    private Boolean active;

    @Column("allowed_paths")
    private String[] allowedPaths;

    @Column("max_callbacks_per_minute")
    private Integer maxCallbacksPerMinute;

    @Column("ip_whitelist")
    private String[] ipWhitelist;

    @Column("require_https")
    private Boolean requireHttps;

    @Column("tenant_id")
    private String tenantId;

    private String notes;
    private String metadata;

    @Column("expires_at")
    private Instant expiresAt;

    @Column("last_callback_at")
    private Instant lastCallbackAt;

    @Column("total_callbacks")
    private Long totalCallbacks;

    @Column("total_failed")
    private Long totalFailed;

    @Column("created_at")
    private Instant createdAt;

    @Column("updated_at")
    private Instant updatedAt;

    @Column("authorized_by")
    private String authorizedBy;
}
