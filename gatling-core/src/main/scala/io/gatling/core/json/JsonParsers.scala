/*
 * Copyright 2011-2024 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.gatling.core.json

import java.io.InputStream

import io.gatling.commons.validation._

import com.fasterxml.jackson.databind.JsonNode

object JsonParsers {
  private val JacksonErrorMapper: String => String = "Jackson failed to parse into a valid AST: " + _
}

final class JsonParsers {
  import JsonParsers._

  def parse(is: InputStream): JsonNode =
    Json.objectMapper.readValue(is, classOf[JsonNode])

  def safeParse(is: InputStream): Validation[JsonNode] =
    safely(JacksonErrorMapper)(parse(is).success)

  def parse(string: String): JsonNode =
    Json.objectMapper.readValue(string, classOf[JsonNode])

  def safeParse(string: String): Validation[JsonNode] =
    safely(JacksonErrorMapper)(parse(string).success)
}
