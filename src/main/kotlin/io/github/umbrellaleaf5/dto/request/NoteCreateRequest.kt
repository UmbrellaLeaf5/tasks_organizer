package io.github.umbrellaleaf5.dto.request

import jakarta.validation.constraints.NotBlank

data class NoteCreateRequest(
  @field:NotBlank(message = "Title is required")
  val title: String,

  val text: String = ""
)
