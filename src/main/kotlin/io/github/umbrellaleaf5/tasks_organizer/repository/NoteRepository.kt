package io.github.umbrellaleaf5.tasks_organizer.repository

import io.github.umbrellaleaf5.tasks_organizer.entity.Note
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository : JpaRepository<Note, Long> {
  fun findAllByAuthorId(authorId: Long): List<Note>

  @Query(
    "SELECT n FROM Note n WHERE " +
            "(LOWER(n.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(n.text) LIKE LOWER(CONCAT('%', :query, '%')))"
  )
  fun findBySearchQuery(@Param("query") query: String): List<Note>

  @Query(
    "SELECT n FROM Note n WHERE n.author.id = :authorId AND " +
            "(LOWER(n.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(n.text) LIKE LOWER(CONCAT('%', :query, '%')))"
  )
  fun findBySearchQueryAndAuthorId(
    @Param("query") query: String,
    @Param("authorId") authorId: Long
  ): List<Note>
}