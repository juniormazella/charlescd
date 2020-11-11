/*
 * Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
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

package io.charlescd.moove.infrastructure.configuration

import feign.Response
import feign.codec.ErrorDecoder
import io.charlescd.moove.domain.MooveErrorCode
import io.charlescd.moove.domain.exceptions.BusinessException
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignErrorDecoderConfiguration {
    @Bean
    fun errorDecoder(): ErrorDecoder {
        return CustomErrorDecoder()
    }
}

class CustomErrorDecoder : ErrorDecoder {
    @Throws(Exception::class)
    override fun decode(methodKey: String?, response: Response?): Exception {
        return when (response?.status()) {
            400 -> IllegalArgumentException(response.reason())
            422 -> BusinessException.of(MooveErrorCode.INVALID_PAYLOAD, response.reason())
            else -> RuntimeException(response?.reason())
        }
    }
}