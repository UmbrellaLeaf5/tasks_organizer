package io.github.umbrellaleaf5.tasks_organizer.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class NoteResponse(
  val id: Long,

  @field:JsonProperty("author_id")
  val authorId: Long,

  val title: String,
  val text: String,

  @field:JsonProperty("created_at")
  val createdAt: Instant
)
