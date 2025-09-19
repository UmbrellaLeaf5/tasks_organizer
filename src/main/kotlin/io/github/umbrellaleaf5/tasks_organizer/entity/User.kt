package io.github.umbrellaleaf5.tasks_organizer.entity

import jakarta.persistence.*

@Entity
@Table(
  name = "users", uniqueConstraints = [
    UniqueConstraint(columnNames = ["short_name"], name = "users_short_name_unique")
  ]
)
data class User(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(name = "short_name", nullable = false)
  val shortName: String,

  @Column(name = "name", nullable = false)
  val name: String,

  @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
  val notes: MutableList<Note> = emptyList<Note>().toMutableList()
) {
  override fun toString(): String {
    return "User(id=$id, shortName='$shortName', name='$name')"
  }
}
