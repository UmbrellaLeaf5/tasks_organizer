package io.github.umbrellaleaf5.tasks_organizer.repository

import io.github.umbrellaleaf5.tasks_organizer.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>
