package io.github.umbrellaleaf5.tasks_organizer.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class UserCreateRequest(
  @field:NotBlank(message = "Short name is required")
  val shortName: String,

  @field:JsonProperty("short_name")
  @field:NotBlank(message = "Name is required")
  val name: String
)
