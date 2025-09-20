package io.github.umbrellaleaf5.tasks_organizer.controller

import io.github.umbrellaleaf5.tasks_organizer.dto.request.UserCreateRequest
import io.github.umbrellaleaf5.tasks_organizer.dto.request.UserUpdateRequest
import io.github.umbrellaleaf5.tasks_organizer.exception.NotFoundException
import io.github.umbrellaleaf5.tasks_organizer.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
  private val userService: UserService
) {

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun getUsers(@RequestParam(name = "id", required = false) userId: Long?): Any {
    return if (userId != null) {
      userService.getUserById(userId) ?: throw NotFoundException("User with id $userId not found")
    } else {
      userService.getAllUsers()
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  fun createUser(@Valid @RequestBody request: UserCreateRequest) {
    userService.createUser(request)
  }

  @PatchMapping
  @ResponseStatus(HttpStatus.OK)
  fun updateUser(
    @RequestParam("id") userId: Long,
    @Valid @RequestBody request: UserUpdateRequest
  ) {
    userService.updateUser(userId, request)
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  fun deleteUser(@RequestParam("id") userId: Long) {
    userService.deleteUser(userId)
  }
}