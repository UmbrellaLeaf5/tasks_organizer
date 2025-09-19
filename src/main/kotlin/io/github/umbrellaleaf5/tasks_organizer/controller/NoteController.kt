package io.github.umbrellaleaf5.tasks_organizer.controller

import io.github.umbrellaleaf5.tasks_organizer.dto.request.NoteCreateRequest
import io.github.umbrellaleaf5.tasks_organizer.dto.request.NoteUpdateRequest
import io.github.umbrellaleaf5.tasks_organizer.dto.response.NoteResponse
import io.github.umbrellaleaf5.tasks_organizer.service.NoteService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/notes")
class NoteController(
  private val noteService: NoteService
) {

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun getNotes(
    @RequestParam(name = "author_id", required = false) authorId: Long?,
    @RequestParam(name = "search", required = false) searchQuery: String?,
    @RequestParam(name = "id", required = false) noteId: Long?
  ): Any {
    return when {
      noteId != null -> noteService.getNoteById(noteId)
        ?: throw IllegalArgumentException("Note not found")

      authorId != null && searchQuery != null -> noteService.searchNotes(searchQuery, authorId)
      authorId != null -> noteService.getNotesByAuthorId(authorId)
      else -> throw IllegalArgumentException("Either author_id, search with author_id, or id must be provided")
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  fun createNote(
    @RequestParam("author_id") authorId: Long,
    @Valid @RequestBody request: NoteCreateRequest
  ): NoteResponse {
    return noteService.createNote(authorId, request)
  }

  @PatchMapping
  @ResponseStatus(HttpStatus.OK)
  fun updateNote(
    @RequestParam("id") noteId: Long,
    @Valid @RequestBody request: NoteUpdateRequest
  ): NoteResponse {
    return noteService.updateNote(noteId, request)
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  fun deleteNote(@RequestParam("id") noteId: Long) {
    noteService.deleteNote(noteId)
  }
}