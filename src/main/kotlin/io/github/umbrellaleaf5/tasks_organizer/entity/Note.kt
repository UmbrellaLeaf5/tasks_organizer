package io.github.umbrellaleaf5.tasks_organizer.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(
  name = "notes", uniqueConstraints = [
    UniqueConstraint(columnNames = ["title"], name = "notes_title_unique")
  ]
)
data class Note(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(name = "title", nullable = false)
  val title: String,

  @Column(name = "text", nullable = false)
  val text: String = "\"\"",

  @Column(name = "created_at", nullable = false)
  val createdAt: Instant,

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
    name = "author_id",
    referencedColumnName = "id",
    nullable = false,
    foreignKey = ForeignKey(name = "notes_author_id_foreign")
  )
  val author: User
) {
  override fun toString(): String {
    return "Note(id=$id, title='$title', authorId=${author.id}, createdAt=$createdAt)"
  }
}
