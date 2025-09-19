package io.github.umbrellaleaf5.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponse(
  val id: Long,
  
  @field:JsonProperty("short_name")
  val shortName: String,
  val name: String
)
