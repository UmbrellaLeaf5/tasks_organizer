package io.github.umbrellaleaf5.tasks_organizer.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class UserUpdateRequest(
  @field:JsonProperty("short_name")
  val shortName: String? = null,

  val name: String? = null
)
