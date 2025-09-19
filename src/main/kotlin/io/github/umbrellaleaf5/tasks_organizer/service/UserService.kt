package io.github.umbrellaleaf5.tasks_organizer.service

import io.github.umbrellaleaf5.tasks_organizer.dto.request.UserCreateRequest
import io.github.umbrellaleaf5.tasks_organizer.dto.request.UserUpdateRequest
import io.github.umbrellaleaf5.tasks_organizer.dto.response.UserResponse
import io.github.umbrellaleaf5.tasks_organizer.entity.User
import io.github.umbrellaleaf5.tasks_organizer.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
  private val userRepository: UserRepository
) {

  fun getAllUsers(): List<UserResponse> {
    return userRepository.findAll().map { it.toResponse() }
  }

  fun getUserById(userId: Long): UserResponse? {
    return userRepository.findByIdOrNull(userId)?.toResponse()
  }

  @Transactional
  fun createUser(request: UserCreateRequest): UserResponse {
    val newUser = User(
      shortName = request.shortName,
      name = request.name
    )

    return userRepository.save(newUser).toResponse()
  }

  @Transactional
  fun updateUser(userId: Long, request: UserUpdateRequest): UserResponse {
    val existingUser = userRepository.findByIdOrNull(userId)
      ?: throw IllegalArgumentException("User with id $userId not found")

    val updatedUser = User(
      id = userId,
      shortName = request.shortName ?: existingUser.shortName,
      name = request.name ?: existingUser.name,
    )

    return userRepository.save(updatedUser).toResponse()
  }

  @Transactional
  fun deleteUser(userId: Long) {
    userRepository.deleteById(userId)
  }

  private fun User.toResponse(): UserResponse {
    return UserResponse(
      id = id!!,
      shortName = shortName,
      name = name
    )
  }
}