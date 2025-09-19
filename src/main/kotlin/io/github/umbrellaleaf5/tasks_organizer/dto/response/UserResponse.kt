package io.github.umbrellaleaf5.tasks_organizer.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponse(
  val id: Long,

  @field:JsonProperty("short_name")
  val shortName: String,
  val name: String
)
