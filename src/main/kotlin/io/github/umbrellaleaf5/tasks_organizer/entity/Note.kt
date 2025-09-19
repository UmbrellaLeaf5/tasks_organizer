package io.github.umbrellaleaf5.tasks_organizer.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "notes")
data class Note(
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notes_seq")
  @SequenceGenerator(
    name = "notes_seq",
    sequenceName = "notes_id_seq",
    allocationSize = 50
  )
  val id: Long? = null,

  @Column(nullable = false)
  val title: String,

  @Column(nullable = false)
  val text: String = "",

  @Column(name = "created_at", nullable = false, updatable = false)
  val createdAt: Instant = Instant.now(),

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "author_id", nullable = false)
  val author: User? = null,
) {
  constructor() : this(null, "", "", Instant.now(), null)

  override fun toString() = "Note(id=$id, title='$title')"
}