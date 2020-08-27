/*
 *
 *  * Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package io.charlescd.moove.application.module.request

import io.charlescd.moove.domain.Component
import io.charlescd.moove.domain.Module
import io.charlescd.moove.domain.User
import java.time.LocalDateTime
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CreateModuleRequest(
    @field:NotBlank
    val name: String,

    @field:NotBlank
    val gitRepositoryAddress: String,

    @field:NotBlank
    val helmRepository: String,

    @field:NotBlank
    val authorId: String,

    @field:Valid
    @field:NotEmpty
    val components: List<ComponentRequest>
) {
    fun toDomain(moduleId: String, workspaceId: String, author: User) = Module(
        id = moduleId,
        name = this.name,
        gitRepositoryAddress = this.gitRepositoryAddress,
        createdAt = LocalDateTime.now(),
        helmRepository = this.helmRepository,
        author = author,
        components = this.components.map { it.toDomain(moduleId, workspaceId) },
        workspaceId = workspaceId
    )
}

data class ComponentRequest(
    @field:NotBlank
    val name: String,

    @field:NotNull
    val errorThreshold: Int,

    @field:NotNull
    val latencyThreshold: Int,

    val hostValue: String?,

    val gatewayName: String?,

    val namespace: String?
) {
    fun toDomain(moduleId: String, workspaceId: String) = Component(
        id = UUID.randomUUID().toString(),
        name = this.name,
        moduleId = moduleId,
        createdAt = LocalDateTime.now(),
        workspaceId = workspaceId,
        errorThreshold = this.errorThreshold,
        latencyThreshold = this.latencyThreshold,
        hostValue = this.hostValue,
        gatewayName = this.gatewayName,
        namespace = this.namespace
    )
}
