package io.github.umbrellaleaf5.tasks_organizer.service

import io.github.umbrellaleaf5.tasks_organizer.dto.request.NoteCreateRequest
import io.github.umbrellaleaf5.tasks_organizer.dto.request.NoteUpdateRequest
import io.github.umbrellaleaf5.tasks_organizer.dto.response.NoteResponse
import io.github.umbrellaleaf5.tasks_organizer.entity.Note
import io.github.umbrellaleaf5.tasks_organizer.exception.NotFoundException
import io.github.umbrellaleaf5.tasks_organizer.repository.NoteRepository
import io.github.umbrellaleaf5.tasks_organizer.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoteService(
  private val noteRepository: NoteRepository,
  private val userRepository: UserRepository
) {

  fun getNotesByAuthorId(authorId: Long): List<NoteResponse> {
    return noteRepository.findAllByAuthorId(authorId).map { it.toResponse() }
  }

  fun searchNotes(query: String, authorId: Long? = null): List<NoteResponse> {
    return if (authorId != null) {
      noteRepository.findBySearchQueryAndAuthorId(query, authorId).map { it.toResponse() }
    } else {
      noteRepository.findBySearchQuery(query).map { it.toResponse() }
    }
  }

  fun getNoteById(noteId: Long): NoteResponse? {
    return noteRepository.findByIdOrNull(noteId)?.toResponse()
  }

  @Transactional
  fun createNote(authorId: Long, request: NoteCreateRequest) {
    val author = userRepository.findById(authorId).orElseThrow {
      NotFoundException("User not found with id $authorId")
    }

    val note = Note(
      title = request.title,
      text = request.text,
      author = author
    )

    noteRepository.save(note)
  }

  @Transactional
  fun updateNote(noteId: Long, request: NoteUpdateRequest) {
    val existingNote = noteRepository.findById(noteId).orElseThrow {
      NotFoundException("Note not found with id $noteId")
    }

    val updatedNote = Note(
      id = noteId,
      title = request.title ?: existingNote!!.title,
      text = request.text ?: existingNote!!.text,
      createdAt = existingNote!!.createdAt,
      author = existingNote.author
    )

    noteRepository.save(updatedNote)
  }

  @Transactional
  fun deleteNote(noteId: Long) {
    noteRepository.deleteById(noteId)
  }

  private fun Note.toResponse(): NoteResponse {
    return NoteResponse(
      id = id!!,
      authorId = author?.id!!,
      title = title,
      text = text,
      createdAt = createdAt
    )
  }
}