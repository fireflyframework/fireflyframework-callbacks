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

package com.firefly.common.callbacks.core.mapper;

import com.firefly.common.callbacks.interfaces.dto.AuthorizedDomainDTO;
import com.firefly.common.callbacks.models.entity.AuthorizedDomain;
import org.mapstruct.Mapper;

/**
 * Mapper for AuthorizedDomain entity and DTO conversion.
 */
@Mapper(componentModel = "spring", uses = JsonMapperHelper.class)
public interface AuthorizedDomainMapper {

    @org.mapstruct.Mapping(source = "metadata", target = "metadata", qualifiedByName = "jsonToMapObject")
    AuthorizedDomainDTO toDto(AuthorizedDomain entity);

    @org.mapstruct.Mapping(source = "metadata", target = "metadata", qualifiedByName = "mapObjectToJson")
    AuthorizedDomain toEntity(AuthorizedDomainDTO dto);
}
