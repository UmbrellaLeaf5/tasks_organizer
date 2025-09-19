package io.github.umbrellaleaf5.tasks_organizer.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
  @SequenceGenerator(
    name = "users_seq",
    sequenceName = "users_id_seq",
    allocationSize = 50
  )
  val id: Long? = null,

  @Column(name = "short_name", nullable = false)
  val shortName: String,

  @Column(nullable = false)
  val name: String
) {
  constructor() : this(null, "", "")

  override fun toString() = "User(id=$id, shortName='$shortName')"
}